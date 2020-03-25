package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.ActivityContract;
import com.example.emmproject.core.DataManager;

import javax.inject.Inject;

public class ActivityPresenter extends BasePresenter<ActivityContract.View> implements ActivityContract.Presenter
{
    private DataManager mDataManager;

    @Inject
    public ActivityPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }
}
