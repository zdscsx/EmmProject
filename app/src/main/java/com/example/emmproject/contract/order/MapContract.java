package com.example.emmproject.contract.order;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.MarkLocationBean;

import java.util.ArrayList;

public interface MapContract {
    interface View extends AbstractView{

        void setMarketList(MarkLocationBean[] locationBeans);
    }

    interface Presenter extends AbstractPresenter<View>{

        void getMarkLocation();
    }
}
