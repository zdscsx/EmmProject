package com.example.emmproject.presenter.order;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.order.SearchContract;
import com.example.emmproject.core.DataManager;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    DataManager mDataManager;

    @Inject
    public SearchPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }
}
