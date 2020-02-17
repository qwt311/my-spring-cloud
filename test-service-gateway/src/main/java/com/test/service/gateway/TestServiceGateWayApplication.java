package com.test.service.gateway;

import com.test.service.gateway.fifter.HostAddrKeyResolver;
import com.test.service.gateway.fifter.RequestTimeGatewayFilterFactory;
import com.test.service.gateway.fifter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class TestServiceGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServiceGateWayApplication.class,args);
    }

//    @Bean
//    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory(){
//        return new RequestTimeGatewayFilterFactory();
//    }

//    @Bean
//    public TokenFilter tokenFilter(){
//        return new TokenFilter();
//    }

//    @Bean
//    public HostAddrKeyResolver hostAddrKeyResolver(){
//        return new HostAddrKeyResolver();
//    }

}
