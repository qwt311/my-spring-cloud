package com.cloud.service.common.response;

import lombok.ToString;

/**
 * 枚举类 ，公共的一些响应码、信息
 * 各子服务应该有单独的XxxxCodeEnum实现ResultCode，不应该混淆
 * 子服务接口文档响应码只需要把公共的和独自的copy上去
 *
 * @author wentao.qiao
 * @date 20120-04-29 10:03:46
 */
@ToString
public enum CommonCodeEnum implements ResultCode {

    /**
     * 公共的响应信息
     */
    SUCCESS(true, "200", "操作成功"),
    ERROR(false, "500", "服务异常"),
    /**
     * 通用码
     */
    COMMON_USE(false, "9999", "操作失败"),
    VALID_ERROR(false, "10000", "参数校验错误"),
    UNCERTIFIED(false, "401", "用户身份未经认证"),
    INVALID_SIGN(false, "-1", "签名校验不通过");

    boolean success;
    String code;
    String message;

    CommonCodeEnum(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
