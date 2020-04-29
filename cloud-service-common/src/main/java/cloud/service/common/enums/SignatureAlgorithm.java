package cloud.service.common.enums;

/**
 * @author wentao.qiao
 * @date 2019/12/10.
 */
public enum  SignatureAlgorithm {
    /**
     * SHA1WithRSA
     */
    SHA1WithRSA("SHA1WithRSA"),

    MD5WithRSA("MD5WithRSA");

    private String signAlgorithm;

    private SignatureAlgorithm(String signAlgorithm) {
        this.signAlgorithm = signAlgorithm;
    }

    public String getSignAlgorithm() {
        return signAlgorithm;
    }

}
