package com.cloud.server.client.controller.sms;

import com.alibaba.fastjson.JSONObject;
import com.cloud.server.client.common.back.sms.SmsReturn;
import com.cloud.server.client.common.request.sms.SendSmsEntity;
import com.cloud.server.client.entity.CloudSmsDeal;
import com.cloud.service.common.annotation.ValidatedParam;
import com.cloud.service.common.constants.ConstantsConfig;
import com.cloud.service.common.exception.BusinessException;
import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  发送短信接口
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@RestController
@RequestMapping(value = "/api/v1.0")
@Slf4j
public class SendSmsCodeController extends BaseSmsController {
    private static final String RSP_CODE = CommonCodeEnum.COMMON_USE.code();
    private static final String API_DESC = "发送短信验证码接口";

    @ValidatedParam(methodDesc = API_DESC)
    @RequestMapping(value = "/sms/send", method = RequestMethod.POST)
    public ApiResult sendSmsCode(@RequestBody SendSmsEntity sendSmsEntity) {
        log.info("{}开始", API_DESC);
        Map<String, Object> body = new HashMap<> (3);
        if (log.isDebugEnabled()) {
            log.debug("{}请求参数:{}",API_DESC , sendSmsEntity);
        }
        boolean sendSms;
        String errorMsg = "";
        try {
            Long duration = sendSmsEntity.getDuration();
            if (duration == null || duration < DEFAULT_SMS_CODE_DURATION) {
                duration = DEFAULT_SMS_CODE_DURATION;
            }
            // 生成6位动态验证码
            String randomCode = ((int) ((Math.random() + 1) * 10000000) + "").trim().substring(1, 7);
            JSONObject jsonObject;
            Object content;
            try {
                content = sendSmsEntity.getContent();
                if(content == null){
                    throw new BusinessException("模板参数不能为空");
                }
                jsonObject = JSONObject.parseObject(JSONObject.toJSONString(content));
                if(jsonObject.isEmpty()){
                    throw new BusinessException("模板参数不能为空");
                }
            }catch (Exception e){
                log.error("{}请求模板参数错误:{}", API_DESC, e.getMessage());
                return ApiResult.buildApiResult(false,RSP_CODE,"请求模板格式不正确");
            }
            jsonObject.put("code", randomCode);
            jsonObject.put("time", duration / 60);

            ApiResult checkRequestNo = checkRequestNo(sendSmsEntity);
            if (checkRequestNo != null) {
                if (log.isDebugEnabled()) {
                    log.debug("{}验证流水号错误，请求信息:{}", API_DESC, sendSmsEntity);
                }
                return checkRequestNo;
            }
            CloudSmsDeal smsDeal = getSmsDeal(sendSmsEntity,randomCode,duration, ConstantsConfig.USER_DEAL_O_STATE_INIT);
            smsDeal.setRequestTime(new Date());
            SmsReturn result;
            if(smsSend){
                //真实发送短信
                result = sendSmsService.sendSms(jsonObject, sendSmsEntity.getMobile(), sendSmsEntity.getBizName(), duration);
            }else{
                result = new SmsReturn();
                result.setSuccess(true);
                result.setMessage("发送成功");
            }
            sendSms = result.getSuccess();
            if(sendSms){
                smsDeal.setStatus(1);
                //只有短信真正发送成功才计费
                cloudSmsDealService.insertSmsDeal(smsDeal);
            }else{
                log.error("{}短信发送错误:{},返回数据:{}", API_DESC, result.getMessage(),result);
                smsDeal.setStatus(0);
                errorMsg = result.getMessage();
            }
        } catch (Exception e) {
            log.error("{}发送短信服务异常:{}", API_DESC, e.getMessage());
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
        if(sendSms){
            body.put("requestNo",sendSmsEntity.getRequestNo());
            return ApiResult.buildApiResult(CommonCodeEnum.SUCCESS,body);
        }else{
            return ApiResult.buildApiResult(false,RSP_CODE, errorMsg);
        }
    }


}