package com.example.emmproject.contract.mine;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.mine.CouponsBean;

import java.util.ArrayList;

public interface CouponsContract {
    interface View extends AbstractView{
     void showCouponsFail();

     void showCoupons(ArrayList<CouponsBean> couponsBeans);

     void showCouponsEmpty();

    }
    interface Presenter extends AbstractPresenter<View>{

        void getCoupons();
    }
}
