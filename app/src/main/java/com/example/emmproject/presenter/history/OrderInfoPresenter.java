package com.example.emmproject.presenter.history;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.history.OrderInfoContract;
import com.example.emmproject.core.DataManager;

import javax.inject.Inject;

public class OrderInfoPresenter extends BasePresenter<OrderInfoContract.View> implements OrderInfoContract.Presenter {
  private   DataManager mDataManager;

  @Inject
    public OrderInfoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }
}
