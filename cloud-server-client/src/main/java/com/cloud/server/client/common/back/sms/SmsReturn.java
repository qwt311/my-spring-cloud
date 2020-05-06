package com.cloud.server.client.common.back.sms;

import java.io.Serializable;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class SmsReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success = true;

    private String message;

    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
