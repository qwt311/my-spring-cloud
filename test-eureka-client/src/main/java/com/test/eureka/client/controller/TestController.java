package com.test.eureka.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Value("${server.port}")
    String port;

    @GetMapping(value = "/time")
    public String index(@RequestParam Long time){
        log.info("请求参数:{}", time);
        try{
            Thread.sleep(time);
        }catch (Exception e){
            log.error("接口/time 出错:{}", e.getMessage());
        }
        return "this request into port: " + port;
    }

}
