package com.example.emmproject.presenter.history;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.history.HistoryListContract;
import com.example.emmproject.core.DataManager;

import javax.inject.Inject;


public class HistoryListPresenter extends BasePresenter<HistoryListContract.View> implements HistoryListContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public HistoryListPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }
}
