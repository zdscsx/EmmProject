package com.example.emmproject.core.bean;

public class LoginByPasswordBean {
    String phone;
    String password;

    public LoginByPasswordBean(String phone, String password) {
        this.phone=phone;
        this.password=password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
