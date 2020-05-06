package com.cloud.server.client.controller.sms;

import	java.util.HashMap;
import java.util.List;
import	java.util.Map;

import com.cloud.server.client.common.request.sms.VerifySmsEntity;
import com.cloud.server.client.entity.CloudSmsDeal;
import com.cloud.service.common.annotation.ValidatedParam;
import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *  短信验证接口
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@RestController
@RequestMapping(value = "/api/v1.0")
@Slf4j
public class VerifySmsController extends BaseSmsController {
    private static final String RSP_CODE = CommonCodeEnum.COMMON_USE.code();
    private static final String API_DESC = "验证短信验证码接口";

    @ValidatedParam(methodDesc = API_DESC)
    @RequestMapping(value = "/sms/verify", method = {RequestMethod.POST})
    public ApiResult verifySms(@RequestBody VerifySmsEntity verifySmsEntity){
        log.info("{}开始", API_DESC);
        if(log.isDebugEnabled()){
            log.debug("{}请求参数:{}", API_DESC, verifySmsEntity);
        }
        String customerNo = verifySmsEntity.getCustomerNo();
        String mobile = verifySmsEntity.getMobile();
        String code = verifySmsEntity.getCode();
        CloudSmsDeal smsDeal;
        Date now;
        try{
            //根据用户编号和手机号查询短信信息
            CloudSmsDeal deal = new CloudSmsDeal();
            deal.setCustomerNo(customerNo);
            deal.setMobile(mobile);
            deal.setStatus(1);
            List<CloudSmsDeal> cloudSmsDealList = cloudSmsDealService.selectWithCondition(deal);
            if(cloudSmsDealList.isEmpty()){
                log.error("{}用户编号为:{}的手机号:{}查询不到信息", API_DESC, customerNo, mobile);
                return ApiResult.buildApiResult(false,RSP_CODE,"当前手机号和用户编号查询不到相关信息");
            }else{
                smsDeal = cloudSmsDealList.get(0);
                now = THREAD_LOCAL.get().parse(THREAD_LOCAL.get().format(new Date()));
            }
        }catch (Exception e){
            log.error("{}短信验证接口出错:{}", API_DESC, e.getMessage());
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
        if(smsSend){
            String checkCode = smsDeal.getCode();
            if(!checkCode.equals(code)){
                log.error("{}用户编号为:{}的手机号:{},验证码错误,发送验证码:{},待验证的验证码:{}",
                        API_DESC,customerNo, mobile, checkCode, code);
                return ApiResult.buildApiResult(false,RSP_CODE,"验证码不正确，请重新输入正确的验证码");
            }else{
                //判断验证码是否已失效
                Date endTime = smsDeal.getEndTime();
                long betweenTime = endTime.getTime() - now.getTime();
                if(betweenTime <= 0){
                    return ApiResult.buildApiResult(false,RSP_CODE,"验证码已失效，请重新发送验证码");
                }
            }
        }
        Map<String, Object> body = new HashMap<> (3);
        body.put("requestNo", verifySmsEntity.getRequestNo());
        return ApiResult.buildApiResult(CommonCodeEnum.SUCCESS,body);
    }

}
