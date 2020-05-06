package com.cloud.server.client.entity;

import java.util.Date;

/**
 * 个人鉴权请求初始化
 */
public class CloudAuthDetails {
    /**
     * 鉴权信息记录表主键id
     */
    private String id;

    /**
     * 鉴权请求时间
     */
    private Date authReqTime;

    /**
     * 鉴权响应时间
     */
    private Date authResTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 鉴权信息记录表主键id
     * @return id 鉴权信息记录表主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 鉴权信息记录表主键id
     * @param id 鉴权信息记录表主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 鉴权请求时间
     * @return auth_req_time 鉴权请求时间
     */
    public Date getAuthReqTime() {
        return authReqTime;
    }

    /**
     * 鉴权请求时间
     * @param authReqTime 鉴权请求时间
     */
    public void setAuthReqTime(Date authReqTime) {
        this.authReqTime = authReqTime;
    }

    /**
     * 鉴权响应时间
     * @return auth_res_time 鉴权响应时间
     */
    public Date getAuthResTime() {
        return authResTime;
    }

    /**
     * 鉴权响应时间
     * @param authResTime 鉴权响应时间
     */
    public void setAuthResTime(Date authResTime) {
        this.authResTime = authResTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}