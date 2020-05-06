package com.cloud.server.client.entity;

import java.util.Date;

/**
 * 短信交易表
 */
public class CloudSmsDeal {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 请求流水号
     */
    private String requestNo;

    /**
     * 懒猫分配开发者编号
     */
    private String customerNo;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码生效时间
     */
    private Date startTime;

    /**
     * 验证码失效时间
     */
    private Date endTime;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 0-失败，1-成功
     */
    private Integer status;

    /**
     * 0-初始化，1-计费成功，2-欠费
     */
    private Integer chargeStatus;

    /**
     * 计费时间
     */
    private Date chargeTime;

    /**
     * 短信通道名称
     */
    private String bizName;

    /**
     * 自增主键
     * @return id 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增主键
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 请求流水号
     * @return request_no 请求流水号
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * 请求流水号
     * @param requestNo 请求流水号
     */
    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo == null ? null : requestNo.trim();
    }

    /**
     * 懒猫分配开发者编号
     * @return customer_no 懒猫分配开发者编号
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * 懒猫分配开发者编号
     * @param customerNo 懒猫分配开发者编号
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    /**
     * 用户手机号码
     * @return mobile 用户手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 用户手机号码
     * @param mobile 用户手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 验证码
     * @return code 验证码
     */
    public String getCode() {
        return code;
    }

    /**
     * 验证码
     * @param code 验证码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 验证码生效时间
     * @return start_time 验证码生效时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 验证码生效时间
     * @param startTime 验证码生效时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 验证码失效时间
     * @return end_time 验证码失效时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 验证码失效时间
     * @param endTime 验证码失效时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 请求时间
     * @return request_time 请求时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 请求时间
     * @param requestTime 请求时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 0-失败，1-成功
     * @return status 0-失败，1-成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0-失败，1-成功
     * @param status 0-失败，1-成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 0-初始化，1-计费成功，2-欠费
     * @return charge_status 0-初始化，1-计费成功，2-欠费
     */
    public Integer getChargeStatus() {
        return chargeStatus;
    }

    /**
     * 0-初始化，1-计费成功，2-欠费
     * @param chargeStatus 0-初始化，1-计费成功，2-欠费
     */
    public void setChargeStatus(Integer chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    /**
     * 计费时间
     * @return charge_time 计费时间
     */
    public Date getChargeTime() {
        return chargeTime;
    }

    /**
     * 计费时间
     * @param chargeTime 计费时间
     */
    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    /**
     * 短信通道名称
     * @return biz_name 短信通道名称
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * 短信通道名称
     * @param bizName 短信通道名称
     */
    public void setBizName(String bizName) {
        this.bizName = bizName == null ? null : bizName.trim();
    }

    @Override
    public String toString() {
        return "CloudSmsDeal{" +
                "id=" + id +
                ", requestNo='" + requestNo + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", requestTime=" + requestTime +
                ", status=" + status +
                ", chargeStatus=" + chargeStatus +
                ", chargeTime=" + chargeTime +
                ", bizName='" + bizName + '\'' +
                '}';
    }
}