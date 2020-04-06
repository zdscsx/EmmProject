package com.example.emmproject.contract.mine;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.mine.ExchangeRequestBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;

public interface UserInfoContract {
    interface View extends AbstractView{
       void showIntegral(IntegralBean integralBean);

       void showIntrgalList(ArrayList<HistoryIntegralBean> integralBeans);

       void showEmpty();


    }

    interface Presenter extends AbstractPresenter<View>{

        void getIntegral();

        void queryIntrgalHistory(int integralState);

        void exchangeCoupons(ExchangeRequestBean exchangeRequestBean);

        void queryIntegralCoupons();
    }
}
