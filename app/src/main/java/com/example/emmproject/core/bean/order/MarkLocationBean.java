package com.example.emmproject.core.bean.order;

import com.google.gson.Gson;

import java.io.Serializable;

public class MarkLocationBean implements Serializable {

public static long serialversionUID=123456789;
    /**
     * chargeId : 123
     * name : 越秀金融大厦充电站
     * address : 广东省广州市天河区珠江东路28号
     * lat : 23.1239700000
     * lng : 113.3261500000
     * workBeginTime : 09:00
     * workEndTime : 20:30
     */

    private int chargeId;
    private String name;
    private String address;
    private String lat;
    private String lng;
    private String workBeginTime;
    private String workEndTime;
    private float distance;

    public static MarkLocationBean objectFromData(String str) {

        return new Gson().fromJson(str, MarkLocationBean.class);
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


    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
