package com.silence.base.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author silence
 * @Description 全局异常处理器
 * @Date 2023/3/24
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Author silence
     * @Description 处理自定义异常
     * @Date 2023/3/24
     */
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse handlerMyException(MyException e) {
        log.error("捕获自定义异常:{}", e.getErrMessage(), e);
        return new RestErrorResponse(e.getErrMessage());
    }


    /**
     * @Author silence
     * @Description 处理系统异常
     * @Date 2023/3/24
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse handleSystemException(Exception e) {
        log.error("捕获系统异常:{}", e.getMessage(), e);
        return new RestErrorResponse(CommonError.UNKNOWN_ERROR.getErrMessage());
    }

}
