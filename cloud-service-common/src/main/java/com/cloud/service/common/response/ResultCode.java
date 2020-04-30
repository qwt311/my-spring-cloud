package com.cloud.service.common.response;

/**
 * 响应信息
 *
 * @author fangzhe.hou 2019-12-3 10:01:50
 */
public interface ResultCode {
    /**
     * 操作是否成功,true为成功，false操作失败
     *
     * @return b
     */
    boolean success();

    /**
     * 操作代码
     *
     * @return code
     */
    String code();

    /**
     * 提示信息
     *
     * @return s
     */
    String message();

}
