package com.guixin.exception;

import lombok.Data;

@Data
public class AjaxResponse {
    private boolean isok; // 请求是否处理成功
    private int code; // 请求响应状态码
    private String message; // 请求结果描述信息
    private Object data; // 请求结果数据

    private AjaxResponse(){}

    // 请求成功的响应，不带数据（用于删除、修改、新增）
    public static AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功！");
        return ajaxResponse;
    }
    // 请求成功的响应，带数据（用于查询）
    public static AjaxResponse success(Object obj){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功！");
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }

    public static AjaxResponse error(CustomException e){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if(e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() + ",系统出现异常，请联系管理员进行处理!");
        }else if (e.getCode() == CustomExceptionType.ACCESS_DENIED.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if (e.getCode() == CustomExceptionType.AUTHENTICATION_ENTRYPOINET.getCode()){
            resultBean.setMessage(e.getMessage());
        } else{
            resultBean.setMessage("系统出现未知异常，请联系管理员进行处理!");
        }
        return resultBean;
    }
}
