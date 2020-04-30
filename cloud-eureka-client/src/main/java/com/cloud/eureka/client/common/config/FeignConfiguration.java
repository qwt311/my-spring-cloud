package com.cloud.eureka.client.common.config;

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *  Feign 配置注册（全局）
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Component
public class FeignConfiguration {

    /**
     *  启用form方式提交参数
     * @return
     */
    @Bean
    public Encoder feignFormEncoder() {
        return new MyEncoder();
    }

}
