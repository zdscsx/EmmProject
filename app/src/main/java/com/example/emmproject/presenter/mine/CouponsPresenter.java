package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.CouponsContract;
import com.example.emmproject.core.DataManager;

import javax.inject.Inject;

public class CouponsPresenter extends BasePresenter<CouponsContract.View> implements CouponsContract.Presenter {
  private   DataManager mDataManger;

  @Inject
    public CouponsPresenter(DataManager mDataManger) {
        this.mDataManger = mDataManger;
    }

    @Override
    public void getCoupons() {

    }
}
