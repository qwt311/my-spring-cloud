package com.cloud.service.common.aspect;

import com.alibaba.fastjson.JSON;
import com.cloud.service.common.annotation.ValidatedParam;
import com.cloud.service.common.response.ApiResult;
import com.cloud.service.common.response.CommonCodeEnum;
import com.cloud.service.common.validator.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

/**
 * 使用spring aop 校验对外请求参数
 *  使用@ValidatedParam 注解的方法
 * @author xiaoqiao
 * @date 2020/04/30
 */
@Aspect
@Component
@Slf4j
public class ValidatedParamAspect {

    @Pointcut("@annotation(com.cloud.service.common.annotation.ValidatedParam)")
    public void validatedParamPointCut(){}

    @Around("validatedParamPointCut()")
    public Object validatedParam(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        ValidatedParam annotation = signature.getMethod().getAnnotation(ValidatedParam.class);
        String methodDesc = annotation.methodDesc();
        int index = annotation.index();
        if(index < 0){
            index = 0;
        }
        if(index > point.getArgs().length){
            index = 0;
        }
        Object params = point.getArgs()[index];
        try {
            ValidatorUtils.validateEntity(params);
            return point.proceed();
        }catch (ValidationException e){
            log.error("{}, 校验参数失败:{}", methodDesc, e.getMessage());
            return JSON.toJSONString(ApiResult.buildApiResult(CommonCodeEnum.VALID_ERROR.success(),CommonCodeEnum.INVALID_SIGN.code(),
                    e.getMessage()));
        }catch(Throwable e){
            log.error("{},执行任务失败:{}", methodDesc, e.getMessage());
            return JSON.toJSONString(ApiResult.buildApiResult(CommonCodeEnum.ERROR));
        }
    }
}
