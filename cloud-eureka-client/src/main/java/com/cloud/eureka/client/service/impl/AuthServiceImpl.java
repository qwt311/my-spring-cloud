package com.cloud.eureka.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.eureka.client.common.back.auth.ent.AuthCompany;
import com.cloud.eureka.client.common.back.auth.ent.AuthResultEnt;
import com.cloud.eureka.client.common.back.auth.personal.AuthPerson;
import com.cloud.eureka.client.common.back.auth.personal.AuthResultPerson;
import com.cloud.eureka.client.common.component.AuthProviderService;
import com.cloud.eureka.client.common.config.ApiConstant;
import com.cloud.eureka.client.common.config.AuthChannelEnum;
import com.cloud.eureka.client.common.config.AuthStatusEntEnum;
import com.cloud.eureka.client.common.config.AuthStatusPersonEnum;
import com.cloud.eureka.client.dao.CloudAuthDetailsMapper;
import com.cloud.eureka.client.dao.CloudAuthRecordInfoMapper;
import com.cloud.eureka.client.dao.CloudAuthUserEntMapper;
import com.cloud.eureka.client.dao.CloudAuthUserPersonalMapper;
import com.cloud.eureka.client.entity.CloudAuthDetailsWithBLOBs;
import com.cloud.eureka.client.entity.CloudAuthRecordInfo;
import com.cloud.eureka.client.entity.CloudAuthUserEnt;
import com.cloud.eureka.client.entity.CloudAuthUserPersonal;
import com.cloud.eureka.client.service.AuthService;
import com.cloud.service.common.enums.AuthEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CloudAuthUserPersonalMapper cloudAuthUserPersonalMapper;

    @Autowired
    private CloudAuthRecordInfoMapper cloudAuthRecordInfoMapper;

    @Autowired
    private CloudAuthDetailsMapper cloudAuthDetailsMapper;

    @Autowired
    private CloudAuthUserEntMapper cloudAuthUserEntMapper;
    @Autowired
    private AuthProviderService authProviderService;

    @Value("${auth.send:false}")
    boolean authSend;

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
    @Override
    public AuthResultEnt authEnt(AuthCompany company){
        AuthResultEnt authResultEnt = new AuthResultEnt();
        String requestNo = company.getRequestNo();
        log.info("企业实名鉴权查询开始");
        CloudAuthUserEnt ent = new CloudAuthUserEnt();
        ent.setCode(company.getCreditCode());
        ent.setName(company.getCorpName());
        ent.setLegalName(company.getLegalPerson());
        ent.setOrgCode(company.getOrgCode());
        ent.setLicenceCode(company.getRegNo());
        authResultEnt.setIp("127.0.0.1");
        authResultEnt.setAuthChannel("");
        List<CloudAuthUserEnt> list = cloudAuthUserEntMapper.selectListWithCondition(ent);
        if(list != null && list.size() > 0){
            authResultEnt.setUseChannel(ApiConstant.UN_USE_CHANNEL);
            authResultEnt.setCertNo(requestNo);
            authResultEnt.setStatus(AuthStatusEntEnum.SUCCESS.getKey());
        }else{
            if(authSend){
                //此处调用真实的鉴权通道
                try{
                    //更新鉴权的请求报文和时间
                    CloudAuthDetailsWithBLOBs cloudAuthDetailsWithBLOBs = new CloudAuthDetailsWithBLOBs();
                    cloudAuthDetailsWithBLOBs.setId(requestNo);
                    cloudAuthDetailsWithBLOBs.setAuthReqJson("");
                    cloudAuthDetailsWithBLOBs.setAuthReqTime(new Date());
                    //调用鉴权通道
                    String authentication = authentication("serviceName");
                    log.info(" requestNo-->{} 企业实名鉴权查询通道返回信息:{}",requestNo,authentication);
                    cloudAuthDetailsWithBLOBs.setAuthResJson(authentication);
                    cloudAuthDetailsWithBLOBs.setAuthResTime(new Date());
                    cloudAuthDetailsMapper.updateByPrimaryKeySelective(cloudAuthDetailsWithBLOBs);
                    JSONObject jsonObject = JSONObject.parseObject(authentication);
                    int code = jsonObject.getIntValue("code");
                    int key;
                    if(code == 0){
                        key = AuthStatusEntEnum.getKey(jsonObject.getString("status"));
                        authResultEnt.setCertNo(jsonObject.getString("certNo"));
                    }else{
                        key = AuthStatusEntEnum.OTHER.getKey();
                    }
                    if(key == AuthStatusEntEnum.SUCCESS.getKey()){
                        //插入企业实名鉴权黑库
                        ent.setAuthTime(new Date());
                        cloudAuthUserEntMapper.insertSelective(ent);
                    }else{
                        authResultEnt.setErrorCode(jsonObject.getString("errorCode"));
                        authResultEnt.setErrorMessage(jsonObject.getString("errorMessage"));
                    }
                    authResultEnt.setUseChannel(ApiConstant.USE_CHANNEL);
                    authResultEnt.setStatus(key);
                }catch (Exception e){
                    log.error("企业鉴权通道异常:{}",e.getMessage());
                    authResultEnt.setUseChannel(ApiConstant.USE_CHANNEL);
                    authResultEnt.setCertNo(requestNo);
                    authResultEnt.setStatus(AuthStatusEntEnum.OTHER.getKey());
                    authResultEnt.setAuthChannel(AuthChannelEnum.YEEPAY_TZT.getKey());
                    authResultEnt.setErrorMessage("鉴权通道异常");
                }
            }else{
                authResultEnt.setUseChannel(ApiConstant.UN_USE_CHANNEL);
                authResultEnt.setCertNo(requestNo);
                authResultEnt.setStatus(AuthStatusEntEnum.SUCCESS.getKey());
                authResultEnt.setAuthChannel(AuthChannelEnum.HEI_KU.getKey());
                //插入企业实名鉴权黑库
                ent.setAuthTime(new Date());
                cloudAuthUserEntMapper.insertSelective(ent);
            }
        }
        authResultEnt.setResponseTime(new Date());
        return authResultEnt;
    }

    /**
     * 个人用户信息鉴权
     * @param person
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
    public AuthResultPerson authPerson(AuthPerson person){
        String requestNo = person.getRequestNo();
        AuthResultPerson authResultPerson = new AuthResultPerson();
        log.info("个人用户信息鉴权查询开始");
        CloudAuthUserPersonal personal = new CloudAuthUserPersonal();
        personal.setIdCardNo(person.getCredNum());
        personal.setName(person.getCardHolder());
        personal.setBankCardNo(person.getCardNo());
        personal.setBank(person.getBank());
        personal.setBankPhone(person.getMobile());
        authResultPerson.setIp(person.getAuthMode());
        //查询黑库记录
        List<CloudAuthUserPersonal> list = cloudAuthUserPersonalMapper.selectListWithConditionNew(personal);
        if(list != null && list.size() > 0){
            authResultPerson.setUseChannel(ApiConstant.UN_USE_CHANNEL);
            //记录表的主键id
            authResultPerson.setAuthNo(requestNo);
            authResultPerson.setAuthChannel(AuthChannelEnum.HEI_KU.getKey());
            authResultPerson.setStatus(AuthStatusPersonEnum.SUCCESS.getKey());
        }else{
            if(authSend){
                authResultPerson.setUseChannel(ApiConstant.USE_CHANNEL);
                person.setSubjectNo("");
                person.setMerchantOrderNo(person.getRequestNo());
                //person.setTradeType("REGISTER");//交易类型
                person.setMerchantNo("");
                person.setAuthMode(person.getAuthMode());
                String json = JSON.toJSONString(person);
                try {

                    //更新鉴权的请求报文和时间
                    CloudAuthDetailsWithBLOBs cloudAuthDetailsWithBLOBs = new CloudAuthDetailsWithBLOBs();
                    cloudAuthDetailsWithBLOBs.setAuthReqJson(json);
                    cloudAuthDetailsWithBLOBs.setId(requestNo);
                    cloudAuthDetailsWithBLOBs.setAuthReqTime(new Date());
                    //鉴权调用
                    String authentication = authentication("serviceName");
                    //更新鉴权的响应报文和时间
                    cloudAuthDetailsWithBLOBs.setId(requestNo);
                    cloudAuthDetailsWithBLOBs.setAuthResJson(authentication);
                    cloudAuthDetailsWithBLOBs.setAuthResTime(new Date());
                    cloudAuthDetailsMapper.updateByPrimaryKeySelective(cloudAuthDetailsWithBLOBs);

                    log.info(" requestNo-->{} 个人实名鉴权通道返回信息:{}",person.getRequestNo(),authentication);
                    JSONObject jsonObject = JSONObject.parseObject(authentication);
                    int code = jsonObject.getIntValue("code");
                    int key;
                    //调用成功
                    if(code == 0){
                        String status = jsonObject.getString("status");
                        key = AuthStatusPersonEnum.getKey(status);
                        authResultPerson.setAuthNo(jsonObject.getString("authNo"));
                    }else{
                        key = AuthStatusPersonEnum.OTHER_ERROR.getKey();
                    }
                    if(key == AuthStatusPersonEnum.SUCCESS.getKey()){
                        //插入个人黑库
                        personal.setAuthTime(new Date());
                        cloudAuthUserPersonalMapper.insertSelectiveNew(personal);
                    }else{
                        authResultPerson.setErrorMessage(jsonObject.getString("errorMessage"));
                        authResultPerson.setErrorCode(jsonObject.getString("errorCode"));
                    }
                    authResultPerson.setStatus(key);
                    authResultPerson.setAuthChannel(jsonObject.getString("authChannel"));
                    authResultPerson.setDescription(jsonObject.getString("description"));
                }catch (Exception e){
                    log.error("个人鉴权通道异常:{}",e.getMessage());
                    authResultPerson.setUseChannel(ApiConstant.USE_CHANNEL);
                    authResultPerson.setAuthNo(requestNo);
                    authResultPerson.setAuthChannel(AuthChannelEnum.YEEPAY_TZT.getKey());
                    authResultPerson.setStatus(AuthStatusPersonEnum.OTHER_ERROR.getKey());
                    authResultPerson.setErrorMessage("鉴权通道异常");
                }
            }else{
                authResultPerson.setUseChannel(ApiConstant.UN_USE_CHANNEL);
                authResultPerson.setAuthNo(requestNo);
                authResultPerson.setAuthChannel(AuthChannelEnum.HEI_KU.getKey());
                authResultPerson.setStatus(AuthStatusPersonEnum.SUCCESS.getKey());
                //插入个人黑库
                personal.setAuthTime(new Date());
                cloudAuthUserPersonalMapper.insertSelectiveNew(personal);
            }
        }
        authResultPerson.setResponseTime(new Date());
        return authResultPerson;
    }

    /**
     * 鉴权实名
     * @param serviceName
     * @return
     */
    private String authentication(String serviceName) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("serviceName",serviceName);
        return authProviderService.auth(map);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Boolean selectAuthRecordByRequestNo(String requestNo) {
        CloudAuthRecordInfo result = cloudAuthRecordInfoMapper.selectByRequestNo(requestNo);
        if(result != null){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
    public boolean initAuthInfoTable(String requestNum, String strData, String authCode,
                                     String requestNo, String customerNo, AuthEnum authEnum) throws Exception {
        try {
            //初始化鉴权记录表
            CloudAuthRecordInfo cloudAuthRecordInfo = new CloudAuthRecordInfo();
            cloudAuthRecordInfo.setId(requestNum);
            cloudAuthRecordInfo.setRequestNo(requestNo);
            cloudAuthRecordInfo.setCustomerNo(customerNo);
            cloudAuthRecordInfo.setAuthCode(authCode);
            cloudAuthRecordInfo.setAuthType(Integer.valueOf(authEnum.val()));
            cloudAuthRecordInfo.setCreateTime(new Date());
            int insert = cloudAuthRecordInfoMapper.insertSelective(cloudAuthRecordInfo);

            //初始化鉴权详情表
            CloudAuthDetailsWithBLOBs cloudAuthDetailsWithBLOBs = new CloudAuthDetailsWithBLOBs();
            cloudAuthDetailsWithBLOBs.setId(requestNum);
            cloudAuthDetailsWithBLOBs.setUserReqJson(strData);
            cloudAuthDetailsWithBLOBs.setCreateTime(new Date());
            int insert2 = cloudAuthDetailsMapper.insertSelective(cloudAuthDetailsWithBLOBs);
            if(insert+insert2 == 2){
                return true;
            }else{
                throw new Exception("记录表和详情表初始化失败！");
            }
        } catch (Exception e) {
            log.info("记录表和详情表初始化异常"+e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int updateAuthUserRecord(CloudAuthRecordInfo cloudAuthRecord) {
        return cloudAuthRecordInfoMapper.updateByPrimaryKeySelective(cloudAuthRecord);
    }

}
