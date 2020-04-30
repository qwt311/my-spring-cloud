package com.cloud.eureka.client.common.request.auth;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *  个人鉴权实体
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Data
@ToString
public class AuthPersonEntity {

    @Size(min = 10, max = 50, message = "用户编号错误")
    @NotBlank(message = "用户编号不能为空")
    private String customerNo;

    @Size(min = 6, max = 50, message = "请求流水号错误")
    @NotBlank(message = "请求流水号不能为空")
    private String requestNo;

    @Size(min = 1, max = 32, message = "银行开户行代码错误")
    private String bank;

    @Size(min = 10, max = 30, message = "银行卡号错误")
    private String cardNo;

    @NotBlank(message = "持卡人姓名不能为空")
    @Size(min = 2, max = 20, message = "持卡人姓名错误")
    private String cardHolder;

    @NotBlank(message = "持卡人手机号不能为空")
    @Pattern(regexp = "^1[3-8][0-9]{9}$", message = "持卡人手机号非法")
    private String mobile;

    @NotBlank(message = "持卡人身份证不能为空")
    @Pattern(regexp = "^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|31)|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}([0-9]|x|X)$", message = "持卡人身份证不合法")
    private String credNum;

    @Size(min = 1, max = 64, message = "鉴权编号错误")
    @NotBlank(message = "鉴权编号")
    private String authCode;
}
