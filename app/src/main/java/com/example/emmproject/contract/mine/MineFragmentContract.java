package com.example.emmproject.contract.mine;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.mine.User;

public interface MineFragmentContract {
    interface View extends AbstractView {

        void showUserInfo(User user);

        void showLoginView();
    }

    interface Presenter extends AbstractPresenter<View>{

         void getUser();
    }
}
