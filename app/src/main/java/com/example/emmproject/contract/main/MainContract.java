package com.example.emmproject.contract.main;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;

public interface MainContract {

    interface View extends AbstractView{

    }

    interface Presenter extends AbstractPresenter<View>{

        void refreshToken();
    }
}
