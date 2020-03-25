package com.example.emmproject.contract.mine;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.mine.CouponsBean;

import java.util.ArrayList;

public interface UnAvailableCouponsContract {
    interface View extends AbstractView{

       void showUnAvailableCoupons(ArrayList<CouponsBean> couponsBeans);

       void showEmptyAvailableCoupons();

    }

    interface Presenter extends AbstractPresenter<View> {

        void getUnavailableCoupons();
    }

}
