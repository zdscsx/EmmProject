package com.example.emmproject.core.bean.history;

import com.google.gson.Gson;

import java.io.Serializable;

public class OrderInfoBean implements Serializable {

    /**
     *                 // 订单信息
     *                 "trade_no":"676782705475584000",
     *                 "originPrice":37.00,    // 优惠前，订单原总价格
     *                 "totalPrice":27.00,    // 优惠后最终总价格
     *                 "createTime":"2020-02-03 19:17",    // 下单时间
     *                 "payTime":"2020-02-03 19:50",        // 支付时间
     *                 "finishTime":"2020-02-03 19:50",    // 完成时间 （取餐时间）
     *                 "payWay":1,    // 支付方式 1->微信支付 2->支付宝支付
     *                 "businessStatus":2,    // 订单业务状态 0->已取消 1->等待支付 2->已支付等待取餐 3->已取餐，订单完成
     *                 "payStatus":2,    // 支付平台定义的支付订单状态，具体见说明文档
     *                 "note":"...",    // 订单备注
     *                 "takeFoodCode":"NrH4rUZ6QGdr/NrIn4AiQw==",    // AES加密后的取餐号
     *                 "takeFoodTime":"12:30",    // 用户约定的取餐时间
     *                 "orderType":1,    // 订单类型 1->自提 2->外卖
     */

    private String trade_no;
    private double originPrice;
    private double totalPrice;
    private String createTime;
    private String payTime;
    private String finishTime;
    private int payWay;
    private int businessStatus;
    private int payStatus;
    private String note;
    private String takeFoodCode;
    private String takeFoodTime;
    private int orderType;

    public static OrderInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, OrderInfoBean.class);
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(int businessStatus) {
        this.businessStatus = businessStatus;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTakeFoodCode() {
        return takeFoodCode;
    }

    public void setTakeFoodCode(String takeFoodCode) {
        this.takeFoodCode = takeFoodCode;
    }

    public String getTakeFoodTime() {
        return takeFoodTime;
    }

    public void setTakeFoodTime(String takeFoodTime) {
        this.takeFoodTime = takeFoodTime;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
