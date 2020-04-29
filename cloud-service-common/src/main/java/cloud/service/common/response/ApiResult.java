package cloud.service.common.response;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 *  对外接口返回通用实体
 * @author wentao.qiao
 * @date 2020/4/29 14:51
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 75463423112353346L;

    /**
     * 操作是否成功
     */
    private boolean success;
    /**
     * 操作代码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    private ApiResult(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResult buildApiResult(ResultCode resultCode){
        return getApiResult(resultCode.success(),resultCode.code(),resultCode.message(), Collections.emptyMap());
    }

    public static ApiResult buildApiResult(ResultCode resultCode, Map<String, Object> data){
        return buildApiResult(resultCode.success(),resultCode.code(),resultCode.message(), data);
    }

    public static ApiResult buildApiResult(boolean success,String code,String message){
        return buildApiResult(success,code,message, Collections.emptyMap());
    }

    public static ApiResult buildApiResult(boolean success,String code,
                                           String message, Map<String, Object> data){
        return getApiResult(success,code,message, data);
    }

    private static<T> ApiResult getApiResult(boolean success,String code,
                                    String message, T data){
        return new ApiResult<> (success, code, message, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
