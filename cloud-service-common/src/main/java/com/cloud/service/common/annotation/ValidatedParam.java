package com.cloud.service.common.annotation;

import java.lang.annotation.*;

/**
 * @author wentao.qiao
 * @DateTime 2020/4/30 13:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidatedParam {

    /**
     *  添加注解的方法描述
     * @return
     */
    String methodDesc() default "";

    /**
     *  注解方法的第几个参数进行参数验证,默认获取第一个参数
     * @return
     */
    int index() default 0;

}
