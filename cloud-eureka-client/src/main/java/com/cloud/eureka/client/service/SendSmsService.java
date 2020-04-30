package com.cloud.eureka.client.service;

import com.alibaba.fastjson.JSONObject;
import com.cloud.eureka.client.common.back.sms.SmsReturn;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public interface SendSmsService {

    /**
     *  发送短信
     * @param content
     * @param receiver
     * @param bizName
     * @param duration
     * @return
     */
    SmsReturn sendSms(JSONObject content, String receiver, String bizName, long duration);
}
