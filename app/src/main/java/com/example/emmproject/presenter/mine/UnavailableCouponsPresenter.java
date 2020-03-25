package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.UnAvailableCouponsContract;
import com.example.emmproject.core.DataManager;

import javax.inject.Inject;

public class UnavailableCouponsPresenter extends BasePresenter<UnAvailableCouponsContract.View> implements UnAvailableCouponsContract.Presenter {
   private DataManager mDataManager;

   @Inject
    public UnavailableCouponsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getUnavailableCoupons() {

    }
}
