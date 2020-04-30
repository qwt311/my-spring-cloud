package com.cloud.service.common.config;

import com.cloud.service.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class RedisHashUtils extends RedisUtils {

    /**
     *  redis 中使用hash保存数据
     */
    public boolean setHashValue(String key, String hKey, Object hValue){
        try{
            redisTemplate.opsForHash().put(key, hKey,hValue);
            return true;
        }catch(Exception e){
            log.error("redis 保存hash结构的数据异常，异常:{}", e.getMessage());
            return false;
        }
    }

    /**
     *  获取redis 中hash结构中指定key的value 中的 key集合
     * @param key   hash key
     * @return
     */
    public Set<Object> getHashKeys(String key){
        try{
            return redisTemplate.opsForHash().keys(key);
        }catch(Exception e){
            log.error("获取redis中hash值得keys集合出错,错误:{}", e.getMessage());
            return null;
        }
    }

    /**
     *  获取redis中路由信息集合
     * @param key
     * @return
     */
    public List<Object> getHashValues(String key){
        try{
            return redisTemplate.opsForHash().values(key);
        }catch(Exception e){
            log.error("获取redis路由信息异常:{}", e.getMessage());
            return null;
        }
    }

    /**
     *  从redis hash中获取指定key、指定value中key的value值
     * @param key
     * @param hashKey
     * @return
     */
    public Object getHashValue(String key, Object hashKey){
        try{
            return redisTemplate.opsForHash().get(key, hashKey);
        }catch(Exception e){
            log.error("获取redis中指定key的hash 值中的value出错，错误:{}", e.getMessage());
            return null;
        }
    }

    /**
     *  删除redis中指定数据的值
     * @param key
     * @param hashKey
     * @return
     */
    public boolean delHashValueKey(String key,Object hashKey){
        try{
            Long delete = redisTemplate.opsForHash().delete(key, hashKey);
            return delete.intValue() == 1;
        }catch(Exception e){
            log.error("删除redis 中指定hash key、value中key的值出错，错误:{}", e.getMessage());
            return false;
        }
    }

    /**
     *  判断redis 的hash中是否存在指定hashKey的数据
     * @param key
     * @param hashKey
     * @return
     */
    public boolean existHashKey(String key, Object hashKey){
        try{
            return redisTemplate.opsForHash().hasKey(key, hashKey);
        }catch(Exception e){
            log.error("判断redis的hash中是否存在指定的数据错误，错误:{}", e.getMessage());
            return false;
        }
    }

    /**
     *  根据hash的key获取所有的值信息
     * @param key
     * @return
     */
    public Map<Object, Object> getRoutes(String key){
        try {
            return redisTemplate.opsForHash().entries(key);
        }catch (Exception e){
            log.error("获取redis路由信息数据失败，key:{}, 错误信息:{}", key, e.getMessage());
            return new HashMap<>(1);
        }
    }

    /**
     *  清空redis 路由信息
     * @param key
     * @return
     */
    public Boolean delHash(String key){
        try{
            return redisTemplate.delete(key);
        }catch(Exception e){
            log.error("清空redis中路由信息失败:{}", e.getMessage());
        }
        return false;
    }
    
}
