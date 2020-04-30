package com.cloud.service.common.exception;

/**
 *  自定义校验参数异常
 * @author wentao.qiao
 * @DateTime 2020/4/30 13:56
 */
public class ValidatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;

    private int code = 500;

    public ValidatedException(String msg){
        super(msg);
        this.msg = msg;
    }

    public ValidatedException(String msg,int code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ValidatedException(String msg, Throwable cause){
        super(msg, cause);
        this.msg = msg;
    }

    public ValidatedException(String msg,int code,Throwable cause){
        super(msg,cause);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
