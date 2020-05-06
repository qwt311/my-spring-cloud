package com.cloud.server.client.common.request.sms;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Data
@ToString
public class VerifySmsEntity {

    @NotBlank(message = "用户编号不能为空")
    @Size(min = 10, max = 50, message = "用户编号错误")
    private String customerNo;

    @Size(min = 6, max = 50, message = "请求流水号错误")
    @NotBlank(message = "请求流水号不能为空")
    private String requestNo;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-8][0-9]{9}$", message = "手机号非法")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码不正确")
    private String code;

}
