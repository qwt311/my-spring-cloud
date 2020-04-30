package com.cloud.eureka.client.entity;

import java.util.Date;

/**
 *  鉴权请求记录
 */
public class CloudAuthRecordInfo {
    /**
     * 自定义主键
     */
    private String id;

    /**
     * 请求流水号
     */
    private String requestNo;

    /**
     * 平台编号
     */
    private String customerNo;

    /**
     * 鉴权类型(0-个人，1-企业)
     */
    private Integer authType;

    /**
     * 认证状态(0-成功，1-认证中，2-认证失败，3-未认证，4-认证出错)
     */
    private Integer status;

    /**
     * 鉴权编号 鉴权类型为个人时：（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）
     *          鉴权类型为企业时： 此参数值为企业信用编码
     */
    private String authCode;

    /**
     * 请求时间
     */
    private Date createTime;

    /**
     * 鉴权响应时间
     */
    private Date responseTime;

    /**
     * 是否使用鉴权渠道(0-未使用，1-使用)
     */
    private Integer useChannel;

    /**
     * 请求数据
     */
    private String requestData;

    /**
     * 计费状态(0-初始化，1-计费成功，2-欠费)
     */
    private Integer chargeStatus;

    /**
     * 计费时间
     */
    private Date chargeTime;

    /**
     * 备注1
     */
    private String remarks1;

    /**
     * 备注2
     */
    private String remarks2;

    /**
     * 备注3
     */
    private String remarks3;

    /**
     * 自定义主键
     * @return id 自定义主键
     */
    public String getId() {
        return id;
    }

    /**
     * 自定义主键
     * @param id 自定义主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 平台编号
     * @return customer_no 平台编号
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * 平台编号
     * @param customerNo 平台编号
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    /**
     * 鉴权类型(0-个人，1-企业)
     * @return auth_type 鉴权类型(0-个人，1-企业)
     */
    public Integer getAuthType() {
        return authType;
    }

    /**
     * 鉴权类型(0-个人，1-企业)
     * @param authType 鉴权类型(0-个人，1-企业)
     */
    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    /**
     * 认证状态(0-成功，1-认证中，2-认证失败，3-未认证，4-认证出错)
     * @return status 认证状态(0-成功，1-认证中，2-认证失败，3-未认证，4-认证出错)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 认证状态(0-成功，1-认证中，2-认证失败，3-未认证，4-认证出错)
     * @param status 认证状态(0-成功，1-认证中，2-认证失败，3-未认证，4-认证出错)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 鉴权编号（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）
     * @return auth_code 鉴权编号（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * 鉴权编号（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）
     * @param authCode 鉴权编号（LIMIT_2-二要素，LIMIT_3-三要素，LIMIT-四要素）
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    /**
     * 请求时间
     * @return create_time 请求时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 请求时间
     * @param createTime 请求时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 鉴权响应时间
     * @return response_time 鉴权响应时间
     */
    public Date getResponseTime() {
        return responseTime;
    }

    /**
     * 鉴权响应时间
     * @param responseTime 鉴权响应时间
     */
    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * 是否使用鉴权渠道(0-未使用，1-使用)
     * @return use_channel 是否使用鉴权渠道(0-未使用，1-使用)
     */
    public Integer getUseChannel() {
        return useChannel;
    }

    /**
     * 是否使用鉴权渠道(0-未使用，1-使用)
     * @param useChannel 是否使用鉴权渠道(0-未使用，1-使用)
     */
    public void setUseChannel(Integer useChannel) {
        this.useChannel = useChannel;
    }

    /**
     * 请求数据
     * @return request_data 请求数据
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * 请求数据
     * @param requestData 请求数据
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData == null ? null : requestData.trim();
    }

    /**
     * 计费状态(0-初始化，1-计费成功，2-欠费)
     * @return charge_status 计费状态(0-初始化，1-计费成功，2-欠费)
     */
    public Integer getChargeStatus() {
        return chargeStatus;
    }

    /**
     * 计费状态(0-初始化，1-计费成功，2-欠费)
     * @param chargeStatus 计费状态(0-初始化，1-计费成功，2-欠费)
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
     * 备注1
     * @return remarks1 备注1
     */
    public String getRemarks1() {
        return remarks1;
    }

    /**
     * 备注1
     * @param remarks1 备注1
     */
    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1 == null ? null : remarks1.trim();
    }

    /**
     * 备注2
     * @return remarks2 备注2
     */
    public String getRemarks2() {
        return remarks2;
    }

    /**
     * 备注2
     * @param remarks2 备注2
     */
    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2 == null ? null : remarks2.trim();
    }

    /**
     * 备注3
     * @return remarks3 备注3
     */
    public String getRemarks3() {
        return remarks3;
    }

    /**
     * 备注3
     * @param remarks3 备注3
     */
    public void setRemarks3(String remarks3) {
        this.remarks3 = remarks3 == null ? null : remarks3.trim();
    }
}