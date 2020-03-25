package com.example.emmproject.core.bean.history;

import com.google.gson.Gson;

public class FoodBean {


    /**
     * foodName : 炒面
     * quantity : 1
     * originSum : 15.0
     * finalSum : 5.0
     * optionInfo : 小份、加辣椒、加鸡蛋
     * mainImage : ...
     */

    private String foodName;
    private int quantity;
    private double originSum;
    private double finalSum;
    private String optionInfo;
    private String mainImage;

    public static FoodBean objectFromData(String str) {

        return new Gson().fromJson(str, FoodBean.class);
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOriginSum() {
        return originSum;
    }

    public void setOriginSum(double originSum) {
        this.originSum = originSum;
    }

    public double getFinalSum() {
        return finalSum;
    }

    public void setFinalSum(double finalSum) {
        this.finalSum = finalSum;
    }

    public String getOptionInfo() {
        return optionInfo;
    }

    public void setOptionInfo(String optionInfo) {
        this.optionInfo = optionInfo;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
}
