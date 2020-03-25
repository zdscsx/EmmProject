package com.example.emmproject.contract.mine;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.luck.picture.lib.entity.LocalMedia;

public interface UserInfoContract {
    interface View extends AbstractView{

    }

    interface Presenter extends AbstractPresenter<View>{
        void unloadPhoto(LocalMedia localMedia);

    }
}
