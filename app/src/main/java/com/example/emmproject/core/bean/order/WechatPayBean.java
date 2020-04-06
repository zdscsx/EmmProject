package com.example.emmproject.core.bean.order;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class WechatPayBean {

    /**
     * payWay : 1
     * trade_no : 123456789
     * signedPayInfo : {"appid":"wxdbbe0c62d03b88ea","noncestr":"R2KlY2cWwpCK3ktL","package":"Sign=WXPay","partnerid":"1580647191","prepayid":"wx272125446934413affa75ec21196331600","sign":"487162A4BA3A1280F6EAF7C50C12DB8E"}
     * orderInfo : {}
     * takeFoodCode : Ngj0dq7GAEXjSSaZKdwWIg==
     */

    private int payWay;
    private int trade_no;
    private SignedPayInfoBean signedPayInfo;
    private OrderInfoBean orderInfo;
    private String takeFoodCode;

    public static WechatPayBean objectFromData(String str) {

        return new Gson().fromJson(str, WechatPayBean.class);
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(int trade_no) {
        this.trade_no = trade_no;
    }

    public SignedPayInfoBean getSignedPayInfo() {
        return signedPayInfo;
    }

    public void setSignedPayInfo(SignedPayInfoBean signedPayInfo) {
        this.signedPayInfo = signedPayInfo;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getTakeFoodCode() {
        return takeFoodCode;
    }

    public void setTakeFoodCode(String takeFoodCode) {
        this.takeFoodCode = takeFoodCode;
    }

    public static class SignedPayInfoBean {
        /**
         * appid : wxdbbe0c62d03b88ea
         * noncestr : R2KlY2cWwpCK3ktL
         * package : Sign=WXPay
         * partnerid : 1580647191
         * prepayid : wx272125446934413affa75ec21196331600
         * sign : 487162A4BA3A1280F6EAF7C50C12DB8E
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;

        public static SignedPayInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, SignedPayInfoBean.class);
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    public static class OrderInfoBean {
        public static OrderInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, OrderInfoBean.class);
        }
    }
}
