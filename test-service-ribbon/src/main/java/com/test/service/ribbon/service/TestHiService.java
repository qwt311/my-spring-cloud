package com.test.service.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestHiService {


    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "timeError")
    public String time(Long time){
        return restTemplate.getForObject("http://test-eureka-client/time?time=" + time,String.class);
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        return restTemplate.getForObject("http://test-eureka-client/hi?name=" + name,String.class);
    }

    public String timeError(Long time){
        return "sorry, what you request time server can not reach, request time :" + time;
    }

    public String hiError(String name){
        return "hi, " + name + ", sorry error!";
    }

}
