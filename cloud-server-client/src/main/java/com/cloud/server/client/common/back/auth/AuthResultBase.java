package com.cloud.server.client.common.back.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class AuthResultBase implements Serializable {

    /**
     * 是否调用鉴权通道 0-调用本地黑库 1-调用鉴权通道
     */
    private int useChannel;
    /**
     * 鉴权状态
     */
    private int status;
    /**
     * 鉴权错误码
     */
    private String errorCode;
    /**
     * 鉴权错误信息
     */
    private String errorMessage;
    /**
     * 调用失败错误描述
     */
    private String description;

    private Date responseTime;

    /**
     * 鉴权ip地址
     */
    private String ip;

    public int getUseChannel() {
        return useChannel;
    }

    public void setUseChannel(int useChannel) {
        this.useChannel = useChannel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
