package com.cloud.eureka.client.entity;

import java.util.Date;

/**
 * 鉴权企业用户黑库
 */
public class CloudAuthUserEnt {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 企业统一社会信用代码(统一社会信用代码和印业执照号码必须有一个有值)
     */
    private String code;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 法人姓名
     */
    private String legalName;

    /**
     * 组织机构代码
     */
    private String orgCode;

    /**
     * 营业执照号码(统一社会信用代码和印业执照号码必须有一个有值)
     */
    private String licenceCode;

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
     * 企业统一社会信用代码(统一社会信用代码和印业执照号码必须有一个有值)
     * @return code 企业统一社会信用代码(统一社会信用代码和印业执照号码必须有一个有值)
     */
    public String getCode() {
        return code;
    }

    /**
     * 企业统一社会信用代码(统一社会信用代码和印业执照号码必须有一个有值)
     * @param code 企业统一社会信用代码(统一社会信用代码和印业执照号码必须有一个有值)
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 企业名称
     * @return name 企业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 企业名称
     * @param name 企业名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 法人姓名
     * @return legal_name 法人姓名
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 法人姓名
     * @param legalName 法人姓名
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    /**
     * 组织机构代码
     * @return org_code 组织机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 组织机构代码
     * @param orgCode 组织机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 营业执照号码(统一社会信用代码和印业执照号码必须有一个有值)
     * @return licence_code 营业执照号码(统一社会信用代码和印业执照号码必须有一个有值)
     */
    public String getLicenceCode() {
        return licenceCode;
    }

    /**
     * 营业执照号码(统一社会信用代码和印业执照号码必须有一个有值)
     * @param licenceCode 营业执照号码(统一社会信用代码和印业执照号码必须有一个有值)
     */
    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode == null ? null : licenceCode.trim();
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