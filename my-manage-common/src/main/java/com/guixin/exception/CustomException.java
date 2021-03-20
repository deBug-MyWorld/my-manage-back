package com.guixin.exception;

public class CustomException extends RuntimeException{
    private int code; // 异常错误编码
    private String message; // 异常信息

    private CustomException(){} // 使用固化后的枚举创建，保护属性

    public CustomException(CustomExceptionType exceptionTypeEnum){
        this.code = exceptionTypeEnum.getCode();
        this.message = exceptionTypeEnum.getDesc();
    }

    public CustomException(CustomExceptionType exceptionTypeEnum,String message){
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
