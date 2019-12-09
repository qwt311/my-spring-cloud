package com.test.service.feign.controller;

import com.test.service.feign.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/feign")
@Slf4j
public class TestFeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/time", method = {RequestMethod.GET})
    public String time(@RequestParam Long time){
        log.info("into feign controller time method, time:{}", time);
        return feignService.time(time);
    }

    @RequestMapping(value = "/hi", method = {RequestMethod.GET})
    public String feign(@RequestParam String name){
        log.info("into feign controller hi method, name:{}", name);
        return feignService.testHi(name);
    }

}
