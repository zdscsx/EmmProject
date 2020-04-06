package com.example.emmproject.contract.mine;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.mine.User;
import com.luck.picture.lib.entity.LocalMedia;

public interface ChangeUserInfoContract {
    interface View extends AbstractView {

       void changePicSuccess();

       void showUser(User user);

       void logoutSuccess();

    }
    interface Presenter extends AbstractPresenter<View>{

        void uploadPic (LocalMedia pic);

        void getUser();

        void changeUserInfo(User user);

        void logout();
    }
}
