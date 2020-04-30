package com.cloud.eureka.client.common.back.auth.personal;

import com.cloud.eureka.client.common.back.auth.AuthResultBase;

/**
 * 个人实名鉴权结果
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class AuthResultPerson extends AuthResultBase {

    /**
     * 支付中心鉴权流水号
     */
    private String authNo;
    /**
     * 鉴权通道
     */
    private String authChannel;

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getAuthChannel() {
        return authChannel;
    }

    public void setAuthChannel(String authChannel) {
        this.authChannel = authChannel;
    }
}
