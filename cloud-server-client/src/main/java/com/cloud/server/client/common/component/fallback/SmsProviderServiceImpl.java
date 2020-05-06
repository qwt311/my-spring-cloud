package com.cloud.server.client.common.component.fallback;

import com.alibaba.fastjson.JSONObject;
import com.cloud.server.client.common.component.SmsProviderService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Service
public class SmsProviderServiceImpl implements SmsProviderService {

    @Override
    public String sms(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","1111");
        jsonObject.put("errorMsg","短信通道异常，进行熔断");
        return jsonObject.toJSONString();
    }
}
