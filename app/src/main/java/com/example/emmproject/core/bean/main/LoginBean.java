package com.example.emmproject.core.bean.main;

public class LoginBean {
    String phone;
    String code;
    String message;

    public LoginBean(String phone, String code,String message) {
        this.phone=phone;
        this.message=message;
        this.code=code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
