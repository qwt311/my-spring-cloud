package com.cloud.eureka.client.controller.auth;

import com.alibaba.fastjson.JSON;
import com.cloud.eureka.client.common.back.auth.personal.AuthPerson;
import com.cloud.eureka.client.common.back.auth.personal.AuthResultPerson;
import com.cloud.eureka.client.common.config.ApiConstant;
import com.cloud.eureka.client.common.config.AuthStatusEntEnum;
import com.cloud.eureka.client.common.config.AuthStatusPersonEnum;
import com.cloud.eureka.client.common.request.auth.AuthPersonEntity;
import com.cloud.eureka.client.entity.CloudAuthRecordInfo;
import com.cloud.service.common.annotation.ValidatedParam;
import com.cloud.service.common.enums.AuthEnum;
import com.cloud.service.common.exception.BusinessException;
import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import com.cloud.service.common.utils.BeanUtil;
import com.cloud.service.common.utils.PrimaryKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *  实名鉴权接口
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@RestController
@RequestMapping(value = "/api/v1.0")
@Slf4j
public class AuthPersonController extends BaseAuthController {
    private static final String API_DESC = "个人实名鉴权接口";
    private static final String RSP_CODE = CommonCodeEnum.COMMON_USE.code();

    @ValidatedParam(methodDesc = API_DESC)
    @RequestMapping(value = "/auth/person", method = {RequestMethod.POST})
    public ApiResult authPerson(@RequestBody AuthPersonEntity authPersonEntity){
        log.info("{}开始", API_DESC);
        if(log.isDebugEnabled()){
            log.debug("{}请求参数:{}", API_DESC, authPersonEntity);
        }
        String primaryKey = PrimaryKeyUtils.getPrimaryKey();
        String authCode = authPersonEntity.getAuthCode();
        try{
            // 用户相关校验
            String checkRequestNo = checkRequestNo(authPersonEntity.getRequestNo());
            if(checkRequestNo != null){
                log.error("{}流水号重复", API_DESC);
                return ApiResult.buildApiResult(false,RSP_CODE,checkRequestNo);
            }
        }catch (Exception e){
            log.error("{}校验用户服务异常:{}", API_DESC, e.getMessage());
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
        try{
            //初始化鉴权详情表和鉴权记录表
            authService.initAuthInfoTable(primaryKey , JSON.toJSONString(authPersonEntity), authCode,
                        authPersonEntity.getRequestNo(),authPersonEntity.getCustomerNo(),AuthEnum.PERSON);
            //鉴权开始
            AuthResult authPersonal = authPersonal(authPersonEntity,authCode, primaryKey);
            if(!authPersonal.isFlag()){
                return ApiResult.buildApiResult(false,RSP_CODE,authPersonal.getErrorMsg());
            }else{
                Map<String, Object> body = new HashMap<> (2);
                body.put("requestNo", authPersonEntity.getRequestNo());
                return ApiResult.buildApiResult(CommonCodeEnum.SUCCESS,body);
            }
        }catch (BusinessException e){
            log.error("{}鉴权失败BusinessException:{}", API_DESC, e.getMessage());
            return ApiResult.buildApiResult(false,RSP_CODE,e.getMessage());
        }catch (Exception e){
            log.error("{}鉴权失败，Exception错误信息:{}", API_DESC, e.getMessage());
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
    }

    /**
     * 个人信息鉴权
     * @param primaryKey    记录表主键
     * @param entity        请求实体
     * @param code          鉴权类型
     * @return AuthResult   鉴权信息
     */
    private AuthResult authPersonal(AuthPersonEntity entity,String code,String primaryKey)
                                    throws BusinessException {
        boolean flag = false;
        AuthResult authResult;
        authResult = new AuthResult();
        AuthPerson personal = new AuthPerson();
        if(StringUtils.equals(AuthEnum.LIMIT2.val(), code) || StringUtils.equals(AuthEnum.LIMIT.val(), code)
                || StringUtils.equals(AuthEnum.LIMIT3.val(), code)){
            personal.setCardHolder(entity.getCardHolder());
            personal.setCredNum(entity.getCredNum());
        }

        if(StringUtils.equals(AuthEnum.LIMIT3.val(), code) || StringUtils.equals(AuthEnum.LIMIT.val(), code)){
            if(!StringUtils.isEmpty(entity.getCardNo())){
                personal.setCardNo(entity.getCardNo());
            }else{
                authResult.setFlag(false);
                authResult.setErrorMsg("银行卡号不能为空");
                return authResult;
            }
        }
        if(StringUtils.equals(AuthEnum.LIMIT.val(), code)){
            if(!StringUtils.isEmpty(entity.getMobile())){
                personal.setMobile(entity.getMobile());
            }else{
                authResult.setFlag(false);
                authResult.setErrorMsg("预留手机号不能为空");
                return authResult;
            }
        }
        personal.setRequestNo(primaryKey);
        personal.setBank(entity.getBank());
        personal.setAuthMode(code);
        //进行个人用户信息鉴定开始
        AuthResultPerson authResultPersonal = authService.authPerson(personal);
        if(authResultPersonal == null){
            authResult.setFlag(false);
            authResult.setErrorMsg("服务开小差了，请联系技术支持人员!");
            return authResult;
        }
        BeanUtil.copyEntity(authResultPersonal, authResult);
        //0-调本地黑库 1-调鉴权通道
        int useChannel = authResultPersonal.getUseChannel();
        //构建鉴权记录实体
        CloudAuthRecordInfo cloudAuthRecord = new CloudAuthRecordInfo();
        cloudAuthRecord.setId(primaryKey);
        cloudAuthRecord.setResponseTime(authResultPersonal.getResponseTime());
        if(useChannel == ApiConstant.UN_USE_CHANNEL){
            //调用本地库验证通过
            cloudAuthRecord.setUseChannel(ApiConstant.UN_USE_CHANNEL);
            cloudAuthRecord.setStatus(AuthStatusPersonEnum.SUCCESS.getKey());
            log.info("在本地库鉴权通过：<---身份证号码为：" + entity.getCredNum());
            flag = true;
        }else if(useChannel == ApiConstant.USE_CHANNEL){
            int status = authResultPersonal.getStatus();
            cloudAuthRecord.setUseChannel(ApiConstant.USE_CHANNEL);
            if(status == 0){
                cloudAuthRecord.setStatus(AuthStatusEntEnum.SUCCESS.getKey());
                log.info("使用鉴权通道通过：<---身份证号码为：" + entity.getCredNum());
                flag =  true;
            }else{
                cloudAuthRecord.setStatus(AuthStatusEntEnum.FAILURE.getKey());
                log.error("鉴权不通过：<---身份证号码为:{},错误信息:{}", entity.getCredNum(), authResultPersonal.getErrorMessage());
                authResult.setErrorMsg(authResultPersonal.getErrorMessage());
                flag = false;
            }
        }
        //更新鉴权记录表
        int flag1 = authService.updateAuthUserRecord(cloudAuthRecord);
        if(flag1 != 1){
            log.error("鉴权记录更新失败");
            throw new BusinessException("鉴权失败---鉴权记录更新失败");
        }
        authResult.setFlag(flag);
        return authResult;
    }

}
