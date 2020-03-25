package com.example.emmproject.core;

import com.google.gson.Gson;

public class BaseResponse<T> {

    T data;

    private boolean status;
    private int code;
    private String message;
    private Object error;

   public BaseResponse(BaseResponse baseResponse){
       this.status=baseResponse.isStatus();
       this.code=baseResponse.getCode();
       this.message=baseResponse.getMessage();
       this.error=baseResponse.getMessage();
   }

    public BaseResponse() {

    }

    public static BaseResponse objectFromData(String str) {

        return new Gson().fromJson(str, BaseResponse.class);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public void setData(T t){
        this.data=t;
    }

    public T getData(){
        return data;
    }
}

