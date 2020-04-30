package com.cloud.eureka.client.common.back.auth.ent;

import com.cloud.eureka.client.common.back.auth.AuthResultBase;

/**
 * 企业实名鉴权结果
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class AuthResultEnt extends AuthResultBase {

    /**
     * 支付中心认证订单号
     */
    private String certNo;

    /**
     * 鉴权通道
     */
    private String authChannel;

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getAuthChannel() {
        return authChannel;
    }

    public void setAuthChannel(String authChannel) {
        this.authChannel = authChannel;
    }
}
