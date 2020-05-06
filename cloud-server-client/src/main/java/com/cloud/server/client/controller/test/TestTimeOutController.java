package com.cloud.server.client.controller.test;
import	java.util.concurrent.TimeUnit;

import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author xiaoqiao
 * @date 2020/05/06
 */
@RestController
@RequestMapping(value = "/api/v1.0")
@Slf4j
public class TestTimeOutController {


    @RequestMapping(value = "/test/readTimeOut", method = {RequestMethod.POST})
    public ApiResult readTimeOut(@RequestParam(name = "time", required = false,defaultValue = "6") Long time,
                                 HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        log.info("{}--测试读取超时接口开始,time:{},header-test:{}",
                Thread.currentThread().getName(), time, request.getHeader("test"));
        if(log.isDebugEnabled()){
            log.debug("用于测试读取超时接口");
        }
        try{
            TimeUnit.SECONDS.sleep(time);
        }catch (Exception e){
            log.error("读取超时接口异常:{}", e.getMessage());
            return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
        }
        long endTime = System.currentTimeMillis();
        log.info("{}--测试读取超时接口结束,耗时:{} 秒", Thread.currentThread().getName(), (endTime - startTime)/1000);
        return ApiResult.buildApiResult(CommonCodeEnum.SUCCESS);
    }

}
