package com.cloud.service.common.enums;

/**
 * 鉴权认证状态
 * @author wentao.qiao
 * @date 2020/04/29.
 */
public enum AuthEnum {

    // 认证类型
    PERSON("0", "个人鉴权"),
    COMPANY("1", "企业认证"),
    // 认证结果
    SUCCESS("SUCCESS", "认证成功"),
    PROCESSING("PROCESSING", "认证中，尚未得到结果"),
    FAILURE("FAILURE", "认证失败"),
    NOT_AUTH("NOT_AUTH", "未认证（不确定是认证成功还是失败）"),
    ERROR("ERROR", "认证出现错误，不确定是成功还是失败"),
	
	//鉴权类型
	LIMIT("LIMIT", "四要素鉴权"),
	LIMIT2("LIMIT_2", "两要素鉴权"),
	LIMIT3("LIMIT_3", "三要素鉴权"),
	
	//用户鉴权服务状态
	AUTH_FORBIDDEN("0", "禁用"),
	AUTH_NORMAL("1", "正常");

    private AuthEnum(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
