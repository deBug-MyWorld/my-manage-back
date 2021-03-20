package com.guixin.exception;

public enum CustomExceptionType {
    USER_INPUT_ERROR("用户输入异常", 400),
    ACCESS_DENIED("权限不足",403),
    AUTHENTICATION_ENTRYPOINET("请求要求用户的身份认证",401),
    SYSTEM_ERROR("系统服务异常", 500),
    OTHER_ERROR("其他未知异常", 999);

    private String desc;// 异常类型中文描述

    private int code; // 响应码

    CustomExceptionType(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
