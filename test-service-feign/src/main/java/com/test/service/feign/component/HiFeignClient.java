package com.test.service.feign.component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "test-eureka-client", fallback = HiFeignClientHystrix.class)
public interface HiFeignClient {

    @RequestMapping(value = "/time", method = {RequestMethod.GET})
    String time(@RequestParam Long time);

    @HystrixCommand()
    @RequestMapping(value = "/hi", method = {RequestMethod.GET})
    String testHi(@RequestParam(value = "name") String name);

}
