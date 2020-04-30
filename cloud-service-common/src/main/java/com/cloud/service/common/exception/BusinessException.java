package com.cloud.service.common.exception;

/**
 *  自定义业务：专门用于业务中抛出，外层进行捕获处理
 * @author wentao.qiao
 * @date 2020/04/29.
 */
public class BusinessException extends Exception {

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(Throwable cause){
        super(cause);
    }

    public BusinessException(String message, Throwable cause){
        super(message, cause);
    }



}
