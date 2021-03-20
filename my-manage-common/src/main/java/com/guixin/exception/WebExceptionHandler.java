package com.guixin.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHandler {
    // 参数校验与绑定  JSR 303
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AjaxResponse handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,fieldError.getDefaultMessage()));
    }
    // 参数校验与绑定 JSR 303
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResponse handleBindException(MethodArgumentNotValidException ex){
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,fieldError.getDefaultMessage()));
    }
    // 当系统抛出的异常是自定义异常时 交给下面方法处理
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e){
        // 日志持久化
        System.out.println("自定义异常");
        return AjaxResponse.error(e);
    }

    // 不合法的参数异常
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public AjaxResponse myArgumentException(IllegalArgumentException e){
        System.out.println(e);
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"不合法的参数异常,请检查参数查询输入"));
    }
    // 权限不足异常
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public AjaxResponse myAccessDeniedException(AccessDeniedException e){
        System.out.println(e);
        return AjaxResponse.error(new CustomException(CustomExceptionType.ACCESS_DENIED));
    }

    // 其他异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse exception(Exception e){
        // 日志持久化
        System.out.println(e);
        System.out.println("未知异常");
        return AjaxResponse.error(new CustomException(CustomExceptionType.OTHER_ERROR,"未知异常"));
    }
}
