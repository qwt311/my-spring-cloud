package com.test.service.feign.service;

import com.test.service.feign.component.HiFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class FeignService {

    @Autowired
    HiFeignClient hiFeignClient;

    public String time(@RequestParam Long time){
        return hiFeignClient.time(time);
    }


    public String testHi(String name){
        return hiFeignClient.testHi(name);
    }

}
