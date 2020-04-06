package com.example.emmproject.core.bean.order;

import com.example.emmproject.core.bean.mine.CouponsBean;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class PrePayInfoBean implements Serializable {

public static long serialVersionUID=789456123;
/**
     * preOrderId : 695016697466109952
     * chargeInfo : {"chargeId":3,"name":"小桔充电站(龙山工业园店)","address":"广东省广州市天河区迎龙路龙山工业园南苑2号\r\n","lat":"23.2097780000","lng":"113.3619660000","workBeginTime":"08:30","workEndTime":"23:00","contact":"020-1234567"}
     * foodList : [{"foodId":33,"foodName":"支付套餐B","optionInfo":"","quantity":1,"originPrice":"0.10","price":"0.01","originSum":"0.10","finalSum":"0.01","mainImage":"https://kpengidrive.com/charging-order/static/image/food/669192189731848192.png"}]
     * giftList : []
     * subTotal : 0.01
     * userId : 33.0
     * availableCoupons : []
     */

    private String preOrderId;
    private ChargeInfoBean chargeInfo;
    private String subTotal;
    private double userId;
    private ArrayList<FoodListBean> foodList;
    private ArrayList<GiftBean> giftList;
    private ArrayList<CouponsBean> availableCoupons;


    public static PrePayInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, PrePayInfoBean.class);
    }

    public String getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(String preOrderId) {
        this.preOrderId = preOrderId;
    }

    public ChargeInfoBean getChargeInfo() {
        return chargeInfo;
    }

    public void setChargeInfo(ChargeInfoBean chargeInfo) {
        this.chargeInfo = chargeInfo;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    public List<FoodListBean> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<FoodListBean> foodList) {
        this.foodList = foodList;
    }

    public List<GiftBean> getGiftList() {
        return giftList;
    }

    public void setGiftList(ArrayList<GiftBean> giftList) {
        this.giftList = giftList;
    }

    public List<CouponsBean> getAvailableCoupons() {
        return availableCoupons;
    }

    public void setAvailableCoupons(ArrayList<CouponsBean> availableCoupons) {
        this.availableCoupons = availableCoupons;
    }

    public static class GiftBean implements Serializable{

        /**
         * giftId : 1
         * giftName : N95口罩
         * mainImage : https://kpengidrive.com/charging-order/static/image/gift/691371005900144640.jpeg
         * quantity : 1
         */

        private int giftId;
        private String giftName;
        private String mainImage;
        private int quantity;

        public static GiftBean objectFromData(String str) {

            return new Gson().fromJson(str, GiftBean.class);
        }

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static class ChargeInfoBean implements Serializable{
        /**
         * chargeId : 3.0
         * name : 小桔充电站(龙山工业园店)
         * address : 广东省广州市天河区迎龙路龙山工业园南苑2号
         * lat : 23.2097780000
         * lng : 113.3619660000
         * workBeginTime : 08:30
         * workEndTime : 23:00
         * contact : 020-1234567
         */

        private double chargeId;
        private String name;
        private String address;
        private String lat;
        private String lng;
        private String workBeginTime;
        private String workEndTime;
        private String contact;

        public static ChargeInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, ChargeInfoBean.class);
        }

        public double getChargeId() {
            return chargeId;
        }

        public void setChargeId(double chargeId) {
            this.chargeId = chargeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getWorkBeginTime() {
            return workBeginTime;
        }

        public void setWorkBeginTime(String workBeginTime) {
            this.workBeginTime = workBeginTime;
        }

        public String getWorkEndTime() {
            return workEndTime;
        }

        public void setWorkEndTime(String workEndTime) {
            this.workEndTime = workEndTime;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }

    public static class FoodListBean implements Serializable,Food {
        /**
         * foodId : 33.0
         * foodName : 支付套餐B
         * optionInfo :
         * quantity : 1.0
         * originPrice : 0.10
         * price : 0.01
         * originSum : 0.10
         * finalSum : 0.01
         * mainImage : https://kpengidrive.com/charging-order/static/image/food/669192189731848192.png
         */

        private double foodId;
        private String foodName;
        private String optionInfo;
        private int quantity;
        private String originPrice;
        private String price;
        private String originSum;
        private String finalSum;
        private String mainImage;

        public static FoodListBean objectFromData(String str) {

            return new Gson().fromJson(str, FoodListBean.class);
        }

        public double getFoodId() {
            return foodId;
        }

        public void setFoodId(double foodId) {
            this.foodId = foodId;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        @Override
        public int getQuantity() {
            return quantity;
        }

        public String getOptionInfo() {
            return optionInfo;
        }

        public void setOptionInfo(String optionInfo) {
            this.optionInfo = optionInfo;
        }



        @Override
        public void setQuantity(int quantity) {
            this.quantity=quantity;

        }

        @Override
        public double getOriginSum() {
            return Double.parseDouble(originPrice);
        }


        public String getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(String originPrice) {
            this.originPrice = originPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }


        @Override
        public void setOriginSum(double originSum) {
            this.originSum=originSum+"";

        }

        @Override
        public double getFinalSum() {
            return Double.parseDouble(finalSum);
        }

        public void setOriginSum(String originSum) {
            this.originSum = originSum;
        }



        @Override
        public void setFinalSum(double finalSum) {
            this.finalSum=finalSum+"";
        }

        public void setFinalSum(String finalSum) {
            this.finalSum = finalSum;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }
    }
}
