package com.cloud.server.client.common.config;

/**
 * 企业实名鉴权状态枚举
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public enum AuthStatusEntEnum {
    /**
     * 认证成功
     */
    SUCCESS(0,"SUCCESS","认证成功"),

    PROCESSING(1,"PROCESSING","认证中，尚未得到结果"),

    FAILURE(2,"FAILURE","认证失败"),

    NOT_AUTH(3,"NOT_AUTH","未认证（不确定是认证成功还是失败）"),

    ERROR(4,"ERROR","认证出现错误，不确定是成功还是失败"),

    OTHER(5,"OTHER","其他鉴权返回错误");

    private int key;

    private String value;

    private String desc;

    AuthStatusEntEnum(int key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public static int getKey(String value){
        AuthStatusEntEnum[] values = AuthStatusEntEnum.values();
        for(AuthStatusEntEnum statusEntEnum: values){
            if(value.equals(statusEntEnum.value)){
                return statusEntEnum.key;
            }
        }
        return OTHER.key;
    }

    public static String getValue(int key){
        AuthStatusEntEnum[] values = AuthStatusEntEnum.values();
        for(AuthStatusEntEnum statusEntEnum: values){
            if(key == statusEntEnum.key){
                return statusEntEnum.value;
            }
        }
        return null;
    }

    public static String getDesc(int key){
        AuthStatusEntEnum[] values = AuthStatusEntEnum.values();
        for(AuthStatusEntEnum statusEntEnum: values){
            if(key == statusEntEnum.key){
                return statusEntEnum.desc;
            }
        }
        return null;
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
