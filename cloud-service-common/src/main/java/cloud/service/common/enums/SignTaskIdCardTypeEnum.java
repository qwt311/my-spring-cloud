package cloud.service.common.enums;

/**
 *  身份证类型枚举
 * @auther wentao.qiao
 * @date 2019/10/2.
 */
public enum  SignTaskIdCardTypeEnum {
    /**
     * 居民身份证
     */
    IDENTITY_CARD_TYPE("0","居民身份证"),

    PASSPORT_TYPE("1","护照"),

    SOLDIER_CARD_TYPE("2","军人身份证"),

    BUSINESS_CARD_TYPE("3","工商登记证"),

    TAX_CARD_TYPE("4","税务登记证"),

    SHAREHOLDER_CARD_TYPE("5","股东代码证"),

    SOCIAL_SECURITY_CARD_TYPE("6","社会保证卡"),

    ORGANIZATION_CODE_TYPE("7","组织机构代码"),

    BUSINESS_LICENSE_TYPE("8","企业营业执照"),

    LEGAL_CARD_TYPE("9","法人代码证"),

    ARMED_POLICE_TYPE("A","武装警察身份证"),

    HK_MC_CARD_TYPE("B","港澳居民往来内地通行证"),

    TW_CARD_TYPE("C","台湾居民来往大陆通行证"),

    RESIDENCE_BOOKLET_TYPE("E","户口簿"),

    TEMP_IDENTITY_CARD_TYPE("F","临时居民身份证"),

    POLICE_CARD_TYPE("G","警察证"),

    INSTITUTION_LEGAL_CARD_TYPE("H","事业单位法人证书"),

    SOCIAL_GROUP_CARD_TYPE("J","社会团体登记证书"),

    NGO_CARD_TYPE("K","民办非企业登记证书"),

    FC_RO_CARD_TYPE("L","外国（地区）企业常驻代表机构登记证"),

    GOVERNMENT_APPROVAL_TYPE("M","政府批文"),

    FOREIGNER_permanent_card_type("P","外国人永久居留证"),

    OTHER_TYPE("Z","其他类型");

    SignTaskIdCardTypeEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *  根据证件类型编号获取描述
     * @param code
     * @return
     */
    public static String getDescWithCode(String code){
        SignTaskIdCardTypeEnum[] values = SignTaskIdCardTypeEnum.values();
        for(SignTaskIdCardTypeEnum cardTypeEnum : values){
            if(cardTypeEnum.code.equals(code)){
                return cardTypeEnum.desc;
            }
        }
        return null;
    }
}
