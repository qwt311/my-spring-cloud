package cloud.service.common.exception;

import cloud.service.common.response.ApiResult;
import cloud.service.common.response.CommonCodeEnum;
import com.fasterxml.jackson.core.JsonParseException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;

/**
 * 异常处理器
 *
 * @author wentao.qiao
 * @date 2020-04-29
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ApiResult arbitrationException(CustomException e) {
        LOGGER.error("错误代码:{}错误信息:{}", e.getCode(), e.getMessage());
        return ApiResult.buildApiResult(false,e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ApiResult ioException(IOException e) {
        LOGGER.error("操作失败:{}", e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ApiResult jsonParseException(JsonParseException e) {
        LOGGER.error("参数报文异常:{}", e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult httpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("Required request body is missing:{}", e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ApiResult handleDuplicateKeyException(DuplicateKeyException e) {
        LOGGER.error(e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }

    @ExceptionHandler(FileUploadException.class)
    public ApiResult handleFileUploadException(FileUploadException e) {
        LOGGER.error(e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }

    @ExceptionHandler(MultipartException.class)
    public ApiResult handleMultipartException(MultipartException e) {
        LOGGER.error(e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }

    /**
     * 服务异常
     *
     * @param e ex
     * @return Exception e
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        LOGGER.error("服务异常{}", e.getMessage(), e);
        return ApiResult.buildApiResult(CommonCodeEnum.ERROR);
    }
}
