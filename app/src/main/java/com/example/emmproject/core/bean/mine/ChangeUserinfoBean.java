package com.example.emmproject.core.bean.mine;

import com.google.gson.Gson;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class ChangeUserinfoBean {

    /**
     * birthday : 2010-04-06
     * gender : 0
     * motorcycleType : 比亚迪 E5
     * userId : 33
     * username : a
     */

    private long birthday;
    private int gender;
    private String motorcycleType;
    private int userId;
    private String username;

    public static ChangeUserinfoBean objectFromData(String str) {

        return new Gson().fromJson(str, ChangeUserinfoBean.class);
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
