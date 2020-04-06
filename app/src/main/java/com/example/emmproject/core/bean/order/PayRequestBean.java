package com.example.emmproject.core.bean.order;

import com.google.gson.Gson;

import java.util.List;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class PayRequestBean {


    /**
     * open_id : oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * preOrderId : 678699813858885632
     * takeFoodTime : 13:00
     * note : 备注
     * payWay : 2
     * finalPrice : 0.00
     * userCouponIds : [10,11]
     */

  //  private String open_id;
    private String preOrderId;
    private String takeFoodTime;
    private String note;
    private int payWay;
    private String finalPrice;
    private List<Integer> userCouponIds;





    public PayRequestBean(String preOrderId, String takeFoodTime, String note, int payWay,
                          String finalPrice, List<Integer> userCouponIds) {
        this.userCouponIds = userCouponIds;
        this.finalPrice = finalPrice;
        this.payWay = payWay;
     //   this.open_id=open_id;
        this.preOrderId=preOrderId;
        this.takeFoodTime=takeFoodTime;
        this.note=note;



    }

    public static PayRequestBean objectFromData(String str) {

        return new Gson().fromJson(str, PayRequestBean.class);
    }



    public String getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(String preOrderId) {
        this.preOrderId = preOrderId;
    }

    public String getTakeFoodTime() {
        return takeFoodTime;
    }

    public void setTakeFoodTime(String takeFoodTime) {
        this.takeFoodTime = takeFoodTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<Integer> getUserCouponIds() {
        return userCouponIds;
    }

    public void setUserCouponIds(List<Integer> userCouponIds) {
        this.userCouponIds = userCouponIds;
    }
}
