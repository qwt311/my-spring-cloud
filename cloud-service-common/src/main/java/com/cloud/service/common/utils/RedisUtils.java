package com.cloud.service.common.utils;

import com.cloud.service.common.constants.ConstantsConfig;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author wentao.qiao
 * @date 2020/04/29.
 */
@Component
public class RedisUtils {
    @Autowired
    protected RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;

    //spring cloud 配置管理后台 redis前缀
    private String befFlag = "cloud:config:";

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != ConstantsConfig.NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, ConstantsConfig.DEFAULT_EXPIRE);
    }

    public void setLm(String key, Object value) {
        set(befFlag + key, value, ConstantsConfig.DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != ConstantsConfig.NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, ConstantsConfig.NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != ConstantsConfig.NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, ConstantsConfig.NOT_EXPIRE);
    }

    public String getLm(String key) {
        return get(befFlag + key, ConstantsConfig.NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteLm(String key) {
        redisTemplate.delete(befFlag + key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * hasKey
     *
     * @param key key
     * @return
     */
    public Boolean hasKey(String key) {
        Boolean aBoolean = redisTemplate.hasKey(key);
        if (aBoolean == null) {
            return false;
        }
        return aBoolean;
    }
}
