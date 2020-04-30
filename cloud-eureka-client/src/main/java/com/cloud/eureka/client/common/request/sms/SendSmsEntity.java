package com.cloud.eureka.client.common.request.sms;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @author wentao.qiao
 * @date 2020/04/30.
 */
@Data
@ToString
public class SendSmsEntity {

    @Size(min = 10, max = 50, message = "用户编号错误")
    @NotBlank(message = "用户编号不能为空")
    private String customerNo;

    @Size(min = 6, max = 50, message = "请求流水号错误")
    @NotBlank(message = "请求流水号不能为空")
    private String requestNo;

    @Pattern(regexp = "^1[3-8][0-9]{9}$", message = "手机号非法")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "短信模板编号不能为空")
    @Max(value = 64, message = "短信模板编号超出限制")
    private String bizName;

    @NotNull(message = "模板参数不能为空")
    private Object content;

    /**
     * 验证码有效时间 单位秒
     */
    private Long duration;

}
