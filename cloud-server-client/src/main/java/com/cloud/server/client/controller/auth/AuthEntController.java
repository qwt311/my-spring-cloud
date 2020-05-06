package com.cloud.server.client.controller.auth;

import	java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cloud.server.client.common.back.auth.ent.AuthCompany;
import com.cloud.server.client.common.back.auth.ent.AuthResultEnt;
import com.cloud.server.client.common.config.ApiConstant;
import com.cloud.server.client.common.config.AuthStatusEntEnum;
import com.cloud.server.client.common.request.auth.AuthEntEntity;
import com.cloud.server.client.entity.CloudAuthRecordInfo;
import com.cloud.service.common.annotation.ValidatedParam;
import com.cloud.service.common.enums.AuthEnum;
import com.cloud.service.common.exception.BusinessException;
import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import com.cloud.service.common.utils.BeanUtil;
import com.cloud.service.common.utils.PrimaryKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 *  企业实名鉴权接口
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1.0")
public class AuthEntController extends BaseAuthController {
    private static final String API_DESC = "企业鉴权接口";
    private static final String RSP_CODE = CommonCodeEnum.COMMON_USE.code();

    @ValidatedParam(methodDesc = API_DESC)
    @RequestMapping(value = "/auth/ent", method = {RequestMethod.POST})
    public ApiResult authEnt(@RequestBody AuthEntEntity authEntEntity){
        log.info("{}底层服务开始", API_DESC);
        if(log.isDebugEnabled()){
            log.debug("{}请求参数:{}", API_DESC, authEntEntity);
        }
        //初始化鉴权详情表和鉴权记录表
        String primaryKey = PrimaryKeyUtils.getPrimaryKey();
        try{
            String checkRequestNo = checkRequestNo(authEntEntity.getRequestNo());
            if(checkRequestNo != null){
                return ApiResult.buildApiResult(CommonCodeEnum.COMMON_USE.success(),CommonCodeEnum.COMMON_USE.code(), checkRequestNo);
            }
            authService.initAuthInfoTable(primaryKey , JSON.toJSONString(authEntEntity),authEntEntity.getCode(),
                    authEntEntity.getRequestNo(),authEntEntity.getCustomerNo(), AuthEnum.COMPANY);
        } catch (Exception e) {
            log.error("{}记录表和详情表初始化失败！！", API_DESC);
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
        AuthResult authResult;
        try{
            authResult = authEnt(authEntEntity, primaryKey);
        }catch (Exception e){
            log.error("{}鉴权失败,错误信息:{}", API_DESC, e.getMessage());
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
        if(authResult.isFlag()){
            Map<String, Object> body = new HashMap<> (3);
            body.put("requestNo", authEntEntity.getRequestNo());
            return ApiResult.buildApiResult(CommonCodeEnum.SUCCESS,body);
        }else{
            return ApiResult.buildApiResult(false,RSP_CODE,authResult.getErrorMsg());
        }
    }

    /**
     *  进行企业鉴权
     * @param entity       企业鉴权请求参数实体
     * @return AuthResult
     */
    private AuthResult authEnt(AuthEntEntity entity,String requestNo) throws BusinessException{
        boolean flag = false;
        AuthResult authResult = new AuthResult();
        AuthCompany company = new AuthCompany();
        company.setRequestNo(requestNo);
        company.setCorpName(entity.getEntName());
        String code = entity.getCode();
        company.setCreditCode(code);
        company.setRegNo(entity.getLicenceNo());
        company.setOrgCode(entity.getOrgCode());
        company.setLegalPerson(entity.getLegalName());
        //进行企业用户信息鉴定开始
        AuthResultEnt authResultEnt = authService.authEnt(company);
        BeanUtil.copyEntity(authResultEnt,authResult);
        if(authResultEnt == null){
            authResult.setFlag(false);
            authResult.setErrorMsg("服务器开小差了");
            return authResult;
        }
        //0-调本地黑库 1-调鉴权通道
        int useChannel = authResultEnt.getUseChannel();
        //构建鉴权记录实体
        CloudAuthRecordInfo cloudAuthRecord = new CloudAuthRecordInfo();
        cloudAuthRecord.setId(requestNo);
        cloudAuthRecord.setResponseTime(authResultEnt.getResponseTime());
        //调用本地库验证通过
        if(useChannel == ApiConstant.UN_USE_CHANNEL){
            //未使用鉴权通道
            cloudAuthRecord.setUseChannel(ApiConstant.UN_USE_CHANNEL);
            cloudAuthRecord.setStatus(AuthStatusEntEnum.SUCCESS.getKey());
            log.info("在本地库鉴权通过：<---统一社会信用代码为：" + code);
            flag = true;
        }else if(useChannel == ApiConstant.USE_CHANNEL){
            int status = authResultEnt.getStatus();
            cloudAuthRecord.setUseChannel(ApiConstant.USE_CHANNEL);
            if(status == 0){
                cloudAuthRecord.setStatus(AuthStatusEntEnum.SUCCESS.getKey());
                log.info("使用渠道鉴权通过：<---统一社会信用代码为:{}", code);
                flag =  true;
            }else{
                cloudAuthRecord.setStatus(AuthStatusEntEnum.FAILURE.getKey());
                log.error("使用渠道鉴权未通过：<---统一社会信用代码为:{}错误描述:{}",code, authResultEnt.getErrorMessage());
                authResult.setErrorMsg(authResultEnt.getErrorMessage());
                flag = false;
            }
        }
        int flag1;
        //更新鉴权记录表
        flag1 = authService.updateAuthUserRecord(cloudAuthRecord);
        if(flag1 != 1){
            log.error("鉴权记录更新失败");
            throw new BusinessException("鉴权失败---鉴权记录更新失败");
        }
        authResult.setFlag(flag);
        return authResult;
    }

}
