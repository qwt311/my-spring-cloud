package com.test.service.ribbon.controller;

import com.test.service.ribbon.service.TestHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHiController {

    @Autowired
    TestHiService testHiService;

    @GetMapping(value = "/time")
    public String time(@RequestParam Long time){
        return testHiService.time(time);
    }

    @GetMapping(value = "/hi")
    public String hiService(@RequestParam String name){
        return testHiService.hiService(name);
    }

}
