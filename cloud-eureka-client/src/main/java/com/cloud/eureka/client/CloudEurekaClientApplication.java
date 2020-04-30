package com.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = { "com.cloud.*"})
public class CloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaClientApplication.class, args);
    }

}
