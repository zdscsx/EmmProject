package com.example.emmproject.core.bean.history;

import com.google.gson.Gson;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class HistoryIntegralBean {

    /**
     * integral_id : 2
     * user_id : 14
     * order_id : 86
     * operate_integral : 1.00
     * status : 1
     * operate_time : 2020-02-17T12:45:19.294+0000
     * description : 交易获得：这是商品描述
     */

    private int integral_id;
    private int user_id;
    private int order_id;
    private String operate_integral;
    private int status;
    private String operate_time;
    private String description;

    public static HistoryIntegralBean objectFromData(String str) {

        return new Gson().fromJson(str, HistoryIntegralBean.class);
    }

    public int getIntegral_id() {
        return integral_id;
    }

    public void setIntegral_id(int integral_id) {
        this.integral_id = integral_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOperate_integral() {
        return operate_integral;
    }

    public void setOperate_integral(String operate_integral) {
        this.operate_integral = operate_integral;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
