package com.cloud.server.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cloud.server.client.common.back.sms.NotifyParam;
import com.cloud.server.client.common.back.sms.SmsReturn;
import com.cloud.server.client.common.component.SmsProviderService;
import com.cloud.server.client.service.SendSmsService;
import com.cloud.service.common.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Slf4j
@Service
public class SendSmsServiceImpl implements SendSmsService {

    private static final String CODE = "0000";

    @Autowired
    private SmsProviderService smsProviderService;

    @Value("${sms.send:false}")
    private boolean smsSend;

    /**
     *  发送短信
     * @param content        短信内容
     * @param receiver       接收者
     * @param bizName        短信模板
     * @param duration       失效时间
     * @return  SmsReturn
     */
    @Override
    public SmsReturn sendSms(JSONObject content, String receiver, String bizName, long duration){
        SmsReturn result = new SmsReturn();
        NotifyParam notifyParam = new NotifyParam();
        notifyParam.setContent(JSONObject.parseObject(content.toJSONString(), new TypeReference<Map<String, String>>(){}));
        notifyParam.setDuration(duration);
        notifyParam.setBizName(bizName);
        notifyParam.setSystemName("");
        notifyParam.setReceiver(receiver);
        notifyParam.setRequestNo(UUIDUtil.getUUID());

        if(smsSend){
            String json = JSON.toJSONString(notifyParam);
            String s = Base64.encodeBase64String(json.getBytes());
            try{
                Map<String, Object> map = new HashMap<>(4);
                map.put("requestData",s);
                String sms = smsProviderService.sms(map);
                log.info("短信通道返回信息:{}", sms);
                JSONObject jsonObject = JSON.parseObject(sms);
                String returnCode = jsonObject.getString("code");
                if(CODE.equals(returnCode)){
                    result.setSuccess(true);
                    result.setMessage("发送成功");
                }else{
                    result.setSuccess(false);
                    result.setMessage(jsonObject.getString("errorMsg"));
                    result.setData(sms);
                }
            } catch (Exception e){
                log.error("发送信息异常:ex:{}",e.getMessage());
                result.setSuccess(false);
                result.setMessage(e.getMessage());
                return result;
            }
        }else{
            log.info("smsSend:{}, 不调用真实短信", smsSend);
            result.setSuccess(true);
            result.setMessage("短信发送成功");
        }

        return result;
    }

}
