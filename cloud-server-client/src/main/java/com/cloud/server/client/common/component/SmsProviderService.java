package com.cloud.server.client.common.component;

import com.cloud.server.client.common.component.fallback.SmsProviderServiceImpl;
import com.cloud.server.client.common.config.FeignConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@FeignClient(name = "sms-service", url = "${sms.url}",
        configuration = {FeignConfiguration.class}, fallback = SmsProviderServiceImpl.class)
public interface SmsProviderService {

    /**
     *  调用真实短信服务，${sms.method} 表示参数从配置文件获取,
     *   请求参数为map类型
     * @param map
     * @return
     */
    @HystrixCommand
    @RequestMapping(value = "${sms.method}", method = RequestMethod.POST,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    String sms(Map<String, Object> map);

}
