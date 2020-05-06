package com.cloud.server.client.entity;

import java.util.Date;

/**
 * 鉴权个人用户黑库
 */
public class CloudAuthUserPersonal {
    /**
     *   自增主键
     */
    private Long id;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 银行编码(银行开户行对应的银行编码)
     */
    private String bank;

    /**
     * 银行卡号码
     */
    private String bankCardNo;

    /**
     * 银行预留手机号码
     */
    private String bankPhone;

    /**
     * 通过鉴权时间
     */
    private Date authTime;

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
     *   自增主键
     * @return id   自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     *   自增主键
     * @param id   自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 身份证号码
     * @return id_card_no 身份证号码
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     * 身份证号码
     * @param idCardNo 身份证号码
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 银行编码(银行开户行对应的银行编码)
     * @return bank 银行编码(银行开户行对应的银行编码)
     */
    public String getBank() {
        return bank;
    }

    /**
     * 银行编码(银行开户行对应的银行编码)
     * @param bank 银行编码(银行开户行对应的银行编码)
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 银行卡号码
     * @return bank_card_no 银行卡号码
     */
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 银行卡号码
     * @param bankCardNo 银行卡号码
     */
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    /**
     * 银行预留手机号码
     * @return bank_phone 银行预留手机号码
     */
    public String getBankPhone() {
        return bankPhone;
    }

    /**
     * 银行预留手机号码
     * @param bankPhone 银行预留手机号码
     */
    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone == null ? null : bankPhone.trim();
    }

    /**
     * 通过鉴权时间
     * @return auth_time 通过鉴权时间
     */
    public Date getAuthTime() {
        return authTime;
    }

    /**
     * 通过鉴权时间
     * @param authTime 通过鉴权时间
     */
    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
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