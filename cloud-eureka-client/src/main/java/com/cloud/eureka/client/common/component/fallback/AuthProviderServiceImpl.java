package com.cloud.eureka.client.common.component.fallback;

import com.alibaba.fastjson.JSONObject;
import com.cloud.eureka.client.common.component.AuthProviderService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *  熔断执行
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Service
public class AuthProviderServiceImpl implements AuthProviderService {

    @Override
    public String auth(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",-1);
        jsonObject.put("errorMessage","请求鉴权服务失败，进行熔断");
        jsonObject.put("errorCode","error_code");
        jsonObject.put("authChannel","NO");
        jsonObject.put("description","鉴权服务异常");
        return jsonObject.toJSONString();
    }
}
