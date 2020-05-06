package com.cloud.server.client.common.request.auth;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *  企业鉴权实体
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Data
@ToString
public class AuthEntEntity {

    @Size(min = 10, max = 50, message = "用户编号错误")
    @NotBlank(message = "用户编号不能为空")
    private String customerNo;

    @Size(min = 6, max = 50, message = "请求流水号错误")
    @NotBlank(message = "请求流水号不能为空")
    private String requestNo;

    @NotBlank(message = "企业名称不能为空")
    @Size(max = 64, message = "企业名称错误")
    private String entName;

    @Pattern(regexp = "[0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}", message = "统一社会信用代码非法")
    private String code;

    @Size(min = 10, max = 18, message = "营业执照号错误")
    private String licenceNo;

    @Size(min = 10, max = 18, message = "组织机构代码错误")
    private String orgCode;

    @NotBlank(message = "法定代表人或代理人姓名不能为空")
    @Size(min = 2, max = 64, message = "法定代表人或代理人姓名错误")
    private String legalName;

}
