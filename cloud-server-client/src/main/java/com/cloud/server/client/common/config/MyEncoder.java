package com.cloud.server.client.common.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.form.spring.SpringFormEncoder;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  将请求参数map 信息转化为form方式提交
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class MyEncoder extends SpringFormEncoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (((ParameterizedTypeImpl) bodyType).getRawType().equals(Map.class)) {
            Map<String,?> data = (Map<String, ?>) object;
            Set<String> nullSet = new HashSet<>();
            for (Map.Entry<String, ?> entry : data.entrySet()) {
                if (entry.getValue() == null) {
                    nullSet.add(entry.getKey());
                }
            }
            for (String s : nullSet) {
                data.remove(s);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
            return;
        }
        super.encode(object, bodyType, template);
    }

}
