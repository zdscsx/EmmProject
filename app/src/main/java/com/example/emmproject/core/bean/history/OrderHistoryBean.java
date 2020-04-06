package com.example.emmproject.core.bean.history;

import com.example.emmproject.core.bean.order.FoodBean;
import com.google.gson.Gson;

import java.io.Serializable;

public class OrderHistoryBean implements Serializable {




    private OrderInfoBean orderInfo;
    private FoodBean[] foodList;
    private int pageNum;
    private int pageSize;
    private ChargeInfoBean chargeInfo;


    public static OrderHistoryBean objectFromData(String str) {

        return new Gson().fromJson(str, OrderHistoryBean.class);
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public FoodBean[] getFoodList() {
        return foodList;
    }

    public void setFoodList(FoodBean[] foodList) {
        this.foodList = foodList;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public ChargeInfoBean getChargeInfo() {
        return chargeInfo;
    }

    public void setChargeInfo(ChargeInfoBean chargeInfo) {
        this.chargeInfo = chargeInfo;
    }

    public static class ChargeInfoBean implements Serializable {
        /**
         * chargeId : 123
         * name : 越秀金融大厦充电站
         * address : 广东省广州市天河区珠江东路28号
         * lat : 23.1239700000
         * lng : 113.3261500000
         * workBeginTime : 00:00
         * workEndTime : 24:00
         * contact : 020-12345678
         */

        private int chargeId;
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

        public int getChargeId() {
            return chargeId;
        }

        public void setChargeId(int chargeId) {
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
}
