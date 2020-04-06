package com.example.emmproject.core.bean.mine;

import com.example.emmproject.app.Constants;
import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class User  implements Serializable {

public static final long serialVersionUID=536871008;

    /**
     * userId : 13
     * username : a13128536051
     * gender : 0
     * birthday : 02-05
     * phone : 13128536051
     * motorcycleType :
     * bind : true
     * integral : 0.00
     * covertIntegral : 0.00
     * avatar : defaultAvatar
     */

    private int userId;
    private String username;
    private int gender;
    private String birthday;
    @Index(unique = true)
    private String phone;
    private String motorcycleType;
    private boolean bind;
    private String integral;
    private String covertIntegral;
    private String avatar;
    @Generated(hash = 1869967722)
    public User(int userId, String username, int gender, String birthday,
            String phone, String motorcycleType, boolean bind, String integral,
            String covertIntegral, String avatar) {
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.motorcycleType = motorcycleType;
        this.bind = bind;
        this.integral = integral;
        this.covertIntegral = covertIntegral;
        this.avatar = avatar;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getGender() {
        return this.gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMotorcycleType() {
        return this.motorcycleType;
    }
    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }
    public boolean getBind() {
        return this.bind;
    }
    public void setBind(boolean bind) {
        this.bind = bind;
    }
    public String getIntegral() {
        return this.integral;
    }
    public boolean isMan(){
        return gender== Constants.SEX_MAN;
    }
    public void setIntegral(String integral) {
        this.integral = integral;
    }
    public String getCovertIntegral() {
        return this.covertIntegral;
    }
    public void setCovertIntegral(String covertIntegral) {
        this.covertIntegral = covertIntegral;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}

