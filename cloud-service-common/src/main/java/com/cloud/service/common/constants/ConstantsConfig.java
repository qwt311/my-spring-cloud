package com.cloud.service.common.constants;

public class ConstantsConfig {

    /**
     *  删除状态- 正常
     */
    public static final int NORMAL = 0;
    /**
     *  删除状态- 删除
     */
    public static final int DEL_FLAG = 1;

    /**
     *  redis 相关配置
     */
    /** 路由key */
    public static final String ROUTE_KEY = "gateway:route";
    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 3600;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;
    /**
     * 计费初始化
     */
    public static final int USER_DEAL_O_STATE_INIT = 0;
}
