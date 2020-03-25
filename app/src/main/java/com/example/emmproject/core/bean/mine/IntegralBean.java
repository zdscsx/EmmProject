package com.example.emmproject.core.bean.mine;

import com.google.gson.Gson;

public class IntegralBean {

    /**
     * userId : 15
     * integral : 160.00
     * covertIntegral : 100.00
     */

    private int userId;
    private String integral;
    private String covertIntegral;

    public static IntegralBean objectFromData(String str) {

        return new Gson().fromJson(str, IntegralBean.class);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCovertIntegral() {
        return covertIntegral;
    }

    public void setCovertIntegral(String covertIntegral) {
        this.covertIntegral = covertIntegral;
    }
}
