package com.cloud.server.client.common.back.auth.personal;

/**
 * 个人实名鉴权实体
 * @author wentao.qiao
 * @date 2020/04/30.
 */
public class AuthPerson {
    /**
     * 请求流水号 -调用方必须传
     */
    private String requestNo;
    /**
     * 平台编号
     */
    private String subjectNo;
    /**
     * 商户订单编号
     */
    private String merchantOrderNo;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 鉴权类型
     */
    private String authMode;
    /**
     * 银行编码 -调用方必须传
     */
    private String bank;
    /**
     * 银行卡号 -调用方必须传
     */
    private String cardNo;
    /**
     * 持卡人姓名 -调用方必须传
     */
    private String cardHolder;
    /**
     * 持卡人手机号 -调用方必须传
     */
    private String mobile;
    /**
     * 持卡人身份证号 -调用方必须传
     */
    private String credNum;
    /**
     * 商户编号
     */
    private String merchantNo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAuthMode() {
        return authMode;
    }

    public void setAuthMode(String authMode) {
        this.authMode = authMode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCredNum() {
        return credNum;
    }

    public void setCredNum(String credNum) {
        this.credNum = credNum;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
}
