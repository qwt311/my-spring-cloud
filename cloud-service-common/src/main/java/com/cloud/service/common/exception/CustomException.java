package com.cloud.service.common.exception;

import com.cloud.service.common.response.ResultCode;

/**
 * 自定义异常
 * 没有public修饰，无法catch，这是Runtime异常
 *
 * @author wentao.qiao
 * @date 2020-04-29 10:39:56
 */
class CustomException extends RuntimeException {

    private String code = "9999";

    CustomException(String msg) {
        super(msg);
    }

    CustomException(String msg, Throwable e) {
        super(msg, e);
    }

    CustomException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    CustomException(String msg, String code, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    CustomException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode.code();
    }

    String getCode() {
        return code;
    }

}
