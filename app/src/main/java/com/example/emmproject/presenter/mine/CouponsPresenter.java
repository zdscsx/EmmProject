package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.CouponsContract;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.utils.RxUtils;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

public class CouponsPresenter extends BasePresenter<CouponsContract.View> implements CouponsContract.Presenter {
  private   DataManager mDataManger;

  @Inject
  public CouponsPresenter(DataManager mDataManger) {
    this.mDataManger = mDataManger;
  }

  @Override
  public void getCoupons() {

    addSubscribe(mDataManger.getCupons()
            .compose(RxUtils.rxSchedulerHelper())
            .subscribeWith(new TipObserver<ArrayList<CouponsBean>>(mView){
              @Override
              public void onSucceed(BaseResponse<ArrayList<CouponsBean>> arrayListBaseResponse) {
                super.onSucceed(arrayListBaseResponse);
                  if (arrayListBaseResponse.getData().size()==0)
                      mView.showCouponsEmpty();
                  else
                      mView.showCoupons(arrayListBaseResponse.getData());
              }

              @Override
              public void onFail(String cause) {
                super.onFail(cause);
                mView.showCouponsEmpty();
              }
            }));
  }
}
