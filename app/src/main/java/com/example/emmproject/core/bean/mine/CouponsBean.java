package com.example.emmproject.core.bean.mine;

import com.google.gson.Gson;

import java.util.List;

public class CouponsBean {


    /**
     * couponUserId : 123
     * couponId : 1
     * couponName : 满50-10券
     * couponType : 1
     * fullAmount : 50.00
     * couponAmount : 10.00
     * validStartTime : 2020-02-16
     * validEndTime : 2020-03-16
     * description : ...
     * status : 0
     * targetType : 1
     * targeFoods : [{"foodId":2,"foodName":"奶茶","optionCode":"1=1"}]
     */
    private int integral;
    private int couponUserId;
    private int couponId;
    private String couponName;
    private int couponType;
    private String fullAmount;
    private String couponAmount;
    private String validStartTime;
    private String validEndTime;
    private String description;
    private int status;
    private int targetType;
    private List<TargeFoodsBean> targeFoods;

    public static CouponsBean objectFromData(String str) {

        return new Gson().fromJson(str, CouponsBean.class);
    }


    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getCouponUserId() {
        return couponUserId;
    }

    public void setCouponUserId(int couponUserId) {
        this.couponUserId = couponUserId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public String getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(String fullAmount) {
        this.fullAmount = fullAmount;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(String validStartTime) {
        this.validStartTime = validStartTime;
    }

    public String getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(String validEndTime) {
        this.validEndTime = validEndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public List<TargeFoodsBean> getTargeFoods() {
        return targeFoods;
    }

    public void setTargeFoods(List<TargeFoodsBean> targeFoods) {
        this.targeFoods = targeFoods;
    }

    public int getCouponId() {
        return couponId;
    }

    private void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public static class TargeFoodsBean {
        /**
         * foodId : 2
         * foodName : 奶茶
         * optionCode : 1=1
         */

        private int foodId;
        private String foodName;
        private String optionCode;

        public static TargeFoodsBean objectFromData(String str) {

            return new Gson().fromJson(str, TargeFoodsBean.class);
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getOptionCode() {
            return optionCode;
        }

        public void setOptionCode(String optionCode) {
            this.optionCode = optionCode;
        }
    }
}
