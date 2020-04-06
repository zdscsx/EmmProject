package com.example.emmproject.core.bean.order;

import com.google.gson.Gson;

import java.util.List;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class SubmitOrderBean {

    /**
     * chargeId : 1
     * foodOptionList : [{"foodId":1,"quantity":2,"optionCode":"1=3&2=2$3"},{"foodId":1,"quantity":2,"optionCode":null}]
     */

    private int chargeId;
    private List<FoodOptionListBean> foodOptionList;

    public SubmitOrderBean(int chargeId, List<FoodOptionListBean> foodOptionListBeans) {
        this.foodOptionList=foodOptionListBeans;
        this.chargeId = chargeId;
    }

    public static SubmitOrderBean objectFromData(String str) {

        return new Gson().fromJson(str, SubmitOrderBean.class);
    }

    public int getChargeId() {
        return chargeId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }

    public List<FoodOptionListBean> getFoodOptionList() {
        return foodOptionList;
    }

    public void setFoodOptionList(List<FoodOptionListBean> foodOptionList) {
        this.foodOptionList = foodOptionList;
    }

    public static class FoodOptionListBean {
        /**
         * foodId : 1
         * quantity : 2
         * optionCode : 1=3&2=2$3
         */

        private int foodId;
        private int quantity;
        private String optionCode;

        public FoodOptionListBean(int foodId, int quantity, String optionCode) {
            this.quantity=quantity;
            this.optionCode=optionCode;
            this.foodId = foodId;
        }

        public static FoodOptionListBean objectFromData(String str) {

            return new Gson().fromJson(str, FoodOptionListBean.class);
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getOptionCode() {
            return optionCode;
        }

        public void setOptionCode(String optionCode) {
            this.optionCode = optionCode;
        }
    }
}
