package com.cloud.service.common.validator;

import com.cloud.service.common.exception.ExceptionCast;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 *
 * @date 2020-04-29 15:50
 */
public abstract class AbstractAssert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            ExceptionCast.cast(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            ExceptionCast.cast(message);
        }
    }
}
