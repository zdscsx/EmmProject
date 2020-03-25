package com.example.emmproject.contract.history;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.history.OrderHistoryBean;

import java.util.ArrayList;

public interface HistoryContract {

    interface View extends AbstractView{

        void showUnLogin();

        void showEmpty();

        void showHistoryList(ArrayList<OrderHistoryBean> historyBeans);
    }

    interface Presenter extends AbstractPresenter<View>{

        void getHistoryList(int historyListType);
    }

}
