package com.klarity.handler;

import com.klarity.common.util.exception.KlarityException;
import com.royee.common.utils.share.exception.BusinessException;
import com.royee.common.utils.share.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: pengcheng091
 * 统一异常处理类
 */
@Configuration
@Slf4j
@ControllerAdvice(basePackages = "com.klarity")
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.info("Global Exception handler initialize...");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(KlarityException.class)
    public Result<?> exceptionHandler(KlarityException e) {
        log.error("GlobalExceptionHandler KlarityException ::: ", e.getThrowable());
        return Result.error(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public Result<?> exceptionHandler(BusinessException e) {
        log.error("GlobalExceptionHandler BusinessException ::: ", e);
        return Result.error(e.getCode(), e.getMessage());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public Result<?> exceptionHandler(RuntimeException e) {
        log.error("GlobalExceptionHandler Exception ::: ", e);
        return Result.error("", "系统异常，请稍后再试");
    }
}