package com.cloud.server.client.common.component;

import com.cloud.server.client.common.component.fallback.AuthProviderServiceImpl;
import com.cloud.server.client.common.config.FeignConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 *  若被调用的服务非spring cloud 微服务，url指定为具体的服务地址，http/https 开头,不会从eureka中获取
 *  鉴权服务代理
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@FeignClient(url = "${auth.url}", name = "auth-service",
        configuration = {FeignConfiguration.class}, fallback = AuthProviderServiceImpl.class)
public interface AuthProviderService {

    /**
     *  使用spring cloud feign调用真实的鉴权服务，${auth.method} 标识参数从配置文件获取，
     *   请求参数为map类型
     * @param map
     * @return
     */
    @HystrixCommand
    @RequestMapping(value = "${auth.method}", method = RequestMethod.POST,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    String auth(Map<String, Object> map);

}
