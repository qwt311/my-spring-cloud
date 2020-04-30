package com.cloud.eureka.client.common.config;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public enum AuthStatusPersonEnum {

    /**
     * 成功
     */
    SUCCESS(0,"SUCCESS","成功"),
    /**
     * 失败
     */
    FAIL(1,"FAIL","失败"),
    /**
     * 出现错误，状态未知，需要后续查询
     */
    ERROR(2,"ERROR","出现错误，状态未知，需要后续查询"),
    /**
     * 预备处理成功
     */
    CONTINUE(3,"CONTINUE","预备处理成功"),
    /**
     * 鉴权有误，可以重试，例如验证码错误
     */
    MISTAKE(4,"MISTAKE","鉴权有误，可以重试，例如验证码错误"),

    OTHER_ERROR(5,"OTHER","其他鉴权返回错误");

    private int key;

    private String value;

    private String desc;

    AuthStatusPersonEnum(int key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public static String getDesc(String value){
        AuthStatusPersonEnum[] values = AuthStatusPersonEnum.values();
        for(AuthStatusPersonEnum statusEnum: values){
            if(value.equals(statusEnum.value)){
                return statusEnum.desc;
            }
        }
        return null;
    }

    public static int getKey(String value){
        AuthStatusPersonEnum[] values = AuthStatusPersonEnum.values();
        for(AuthStatusPersonEnum statusEnum: values){
            if(value.equals(statusEnum.value)){
                return statusEnum.key;
            }
        }
        return OTHER_ERROR.key;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
