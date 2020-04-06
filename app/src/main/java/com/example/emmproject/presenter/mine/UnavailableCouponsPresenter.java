package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.UnAvailableCouponsContract;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.utils.RxUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class UnavailableCouponsPresenter extends BasePresenter<UnAvailableCouponsContract.View> implements UnAvailableCouponsContract.Presenter {
   private DataManager mDataManager;

   @Inject
    public UnavailableCouponsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getUnavailableCoupons() {
        addSubscribe(mDataManager.getUnAvailableCupons()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver<ArrayList<CouponsBean>>(mView){
                    @Override
                    public void onSucceed(BaseResponse<ArrayList<CouponsBean>> arrayListBaseResponse) {
                        super.onSucceed(arrayListBaseResponse);
                        if (arrayListBaseResponse.getData().size()==0)
                            mView.showEmptyAvailableCoupons();
                        else
                        mView.showUnAvailableCoupons(arrayListBaseResponse.getData());
                    }

                    @Override
                    public void onFail(String cause) {
                        super.onFail(cause);
                        mView.showEmptyAvailableCoupons();
                    }
                }));
    }
    }

