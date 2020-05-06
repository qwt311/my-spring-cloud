package com.cloud.server.client.service;

import com.cloud.server.client.common.back.auth.ent.AuthCompany;
import com.cloud.server.client.common.back.auth.ent.AuthResultEnt;
import com.cloud.server.client.common.back.auth.personal.AuthPerson;
import com.cloud.server.client.common.back.auth.personal.AuthResultPerson;
import com.cloud.server.client.entity.CloudAuthRecordInfo;
import com.cloud.service.common.enums.AuthEnum;

/**
 *  鉴权服务
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public interface AuthService {

    /**
     * 个人用户信息鉴权
     * @param person
     * @return
     */
    AuthResultPerson authPerson(AuthPerson person);

    /**
     *  企业鉴权
     * @param company
     * @return
     */
    AuthResultEnt authEnt(AuthCompany company);

	Boolean selectAuthRecordByRequestNo(String requestNo);

	boolean initAuthInfoTable(String requestNum, String strData, String authCode,
                              String requestNo, String customerNo, AuthEnum authEnum) throws Exception ;

	int updateAuthUserRecord(CloudAuthRecordInfo lmlyAuthRecord);
}
