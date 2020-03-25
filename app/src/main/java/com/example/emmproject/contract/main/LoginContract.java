package com.example.emmproject.contract.main;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.LoginByPasswordBean;

public interface LoginContract {
    interface View extends AbstractView{

        void loginSuccess();

        void loginFail();

        void getCodeSuccess(String message);



    }

    interface Presenter extends AbstractPresenter<View>{
       void getVreifyCode(String phoneNumber);

       void wechatLogin();

       void loginByPassword(LoginByPasswordBean loginByPasswordBean);
    }
}
