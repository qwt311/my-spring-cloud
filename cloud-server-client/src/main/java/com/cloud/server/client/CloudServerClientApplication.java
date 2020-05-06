package com.cloud.server.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = { "com.cloud.*"})
public class CloudServerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServerClientApplication.class, args);
    }

}
