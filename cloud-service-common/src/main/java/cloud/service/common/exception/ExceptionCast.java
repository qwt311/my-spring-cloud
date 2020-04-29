package cloud.service.common.exception;


import cloud.service.common.response.ResultCode;

/**
 * 抛出异常工具
 *
 * @author wentao.qiao
 * @date 2019-12-3 10:41:59
 **/
public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }

    public static void cast(String code, String msg) {
        throw new CustomException(msg, code);
    }

    public static void cast(String msg) {
        throw new CustomException(msg);
    }
}
