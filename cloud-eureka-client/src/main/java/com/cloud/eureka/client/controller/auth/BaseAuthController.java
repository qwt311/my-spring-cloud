package com.cloud.eureka.client.controller.auth;

import com.cloud.eureka.client.service.AuthService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Slf4j
public class BaseAuthController {

    @Autowired
    protected AuthService authService;

    /**
     *  校验用户
     * @param requestNo
     * @return
     */
    String checkRequestNo(String requestNo){
        //交易流水号是否重复
        Boolean existFlag = authService.selectAuthRecordByRequestNo(requestNo);
        if(existFlag){
            log.error("用户请求流水号重复:{}", requestNo);
            return "请求重复,请更换流水号进行请求";
        }
        return null;
    }

    @Data
    class AuthResult{
        private static final long serialVersionUID = 1L;
        /**
         * 鉴权是否通过
         */
        private boolean flag;
        /**
         * 鉴权没过的失败信息
         */
        private String errorMsg;
    }

}
