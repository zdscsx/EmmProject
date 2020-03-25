package com.example.emmproject.core.bean.mine;

public class CouponsDataBean {
    CouponsBean[] availableCoupons;
    CouponsBean[] unAvailableCoupons;

    public CouponsBean[] getAvailableCoupons() {
        return availableCoupons;
    }

    public void setAvailableCoupons(CouponsBean[] availableCoupons) {
        this.availableCoupons = availableCoupons;
    }

    public CouponsBean[] getUnAvailableCoupons() {
        return unAvailableCoupons;
    }

    public void setUnAvailableCoupons(CouponsBean[] unAvailableCoupons) {
        this.unAvailableCoupons = unAvailableCoupons;
    }
}
