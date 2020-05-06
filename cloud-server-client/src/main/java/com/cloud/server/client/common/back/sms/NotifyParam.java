package com.cloud.server.client.common.back.sms;

import java.io.Serializable;
import java.util.Map;

/**
 * 发送短信实体
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class NotifyParam implements Serializable{

    private static final long serialVersionUID = 4654671469794556979L;

    private Map<String,String> content;

    private long duration;

    private String bizName;

    private String systemName;

    private String receiver;

    private String requestNo;

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
