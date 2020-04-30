package com.cloud.eureka.client.common.config;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public enum AuthChannelEnum {

    /**
     * 本地黑库
     */
    HEI_KU("BDHK","本地黑库"),
    /**
     * 易宝-投资通
     */
    YEEPAY_TZT("YEEPAY_TZT","易宝-投资通");

    private String key;

    private String value;

    AuthChannelEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
