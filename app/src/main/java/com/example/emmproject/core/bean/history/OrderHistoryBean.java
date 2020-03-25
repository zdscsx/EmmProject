package com.example.emmproject.core.bean.history;

import com.google.gson.Gson;

public class OrderHistoryBean {

    /**
     *             "chargeId": 123, // 该充电场站唯一id
     *             "name": "越秀金融大厦充电站",     // 名字
     *             "address": "广东省广州市天河区珠江东路28号",    // 地址
     *             "lat": "23.1239700000",        // 纬度
     *             "lng": "113.3261500000",    // 经度
     *             "workBeginTime": "00:00",
     *             "workEndTime": "24:00",
     *             "contact":"020-12345678"    // 联系时间
     *               "foodList":[    // 订单内餐品明细信息
     *             {
     *                 "foodName":"扬州炒饭",
     *                 "quantity":2,    // 数量
     *                 "originSum":22.00,    // 原价总计
     *                 "finalSum":22.00,    // 优惠后实际价格
     *                 "optionInfo": "小份、加辣椒、加鸡蛋",    //规格选项描述
     *                 "mainImage":"..."    // 主图url
     *             },
     *             {
     *                 "foodName":"炒面",
     *                 "quantity":1,    // 数量
     *                 "originSum":15.00,    // 原价总计
     *                 "finalSum":5.00,    // 优惠后实际价格
     *                 "optionInfo": "小份、加辣椒、加鸡蛋",    //规格选项描述
     *                 "mainImage":"..."    // 主图url
     *             }],
     *         },{订单二},{订单三}.....
     *         ]
     *         "pageNum":1,
     *         "pageSize":10
     */

    private int chargeId;
    private String name;
    private String address;
    private String lat;
    private String lng;
    private String workBeginTime;
    private String workEndTime;
    private String contact;
    private OrderInfoBean orderInfo;
    private FoodBean[] foodList;
    private int pageNum;
    private int pageSize;
    public static OrderHistoryBean objectFromData(String str) {

        return new Gson().fromJson(str, OrderHistoryBean.class);
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
}
