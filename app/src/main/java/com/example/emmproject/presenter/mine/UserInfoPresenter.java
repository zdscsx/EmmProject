package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.UserInfoContract;
import com.example.emmproject.core.DataManager;
import com.luck.picture.lib.entity.LocalMedia;

import javax.inject.Inject;

public class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {
   private DataManager mDataManager;


   @Inject
    public UserInfoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void unloadPhoto(LocalMedia localMedia) {

    }
}
