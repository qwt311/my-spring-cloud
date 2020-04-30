package com.cloud.eureka.client.common.back.auth.ent;

/**
 * 企业实名鉴权实体
 * @author wentao.qiao
 * @date 2019/12/10.
 */
public class AuthCompany {
    /**
     * 请求流水号 -调用方必须传
     */
    private String requestNo;
    /**
     * 平台编号
     */
    private String subjectNo;
    /**
     * 企业名称（全称） -调用方必须传
     */
    private String corpName;
    /**
     * 统一社会信用代码 -调用方必须传(与营业执照号两者必须传一个)
     */
    private String creditCode;
    /**
     * 营业执照号（与统一社会信用代码二者必传一个）
     */
    private String regNo;
    /**
     * 组织机构代码 ( -调用方可以不传)
     */
    private String orgCode;
    /**
     * 法人姓名 -调用方必须传
     */
    private String legalPerson;
    /**
     * 发起方
     */
    private String initiator;
    /**
     * 会员编号
     */
    private String memberNo;

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

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
