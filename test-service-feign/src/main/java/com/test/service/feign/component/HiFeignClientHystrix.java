package com.test.service.feign.component;

import org.springframework.stereotype.Component;

@Component
public class HiFeignClientHystrix implements HiFeignClient {

    @Override
    public String time(Long time) {
        return "sorry what you request time server can not reach, please wait minutes";
    }

    @Override
    public String testHi(String name) {
        return "sorry: " + name + " , what you request testHi server can not reach";
    }
}
