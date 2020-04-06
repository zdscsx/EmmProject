package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.UserInfoContract;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.example.emmproject.utils.RxUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

public class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {
   private DataManager mDataManager;


   @Inject
    public UserInfoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }



    @Override
    public void getIntegral() {
       addSubscribe( mDataManager.getIntegral()
       .compose(RxUtils.rxSchedulerHelper()).subscribeWith(new TipObserver<IntegralBean>(mView){
                   @Override
                   public void onSucceed(BaseResponse<IntegralBean> integralBeanBaseResponse) {
                       super.onSucceed(integralBeanBaseResponse);
                       mView.showIntegral(integralBeanBaseResponse.getData());
                   }
               }));
    }

    @Override
    public void queryIntrgalHistory() {
       addSubscribe(mDataManager.queryIntegralHistory()
       .compose(RxUtils.rxSchedulerHelper())
       .subscribeWith(new TipObserver<ArrayList<HistoryIntegralBean>>(mView){

           @Override
           public void onFail(String cause) {
               super.onFail(cause);
               mView.showEmpty();
           }

           @Override
           public void onSucceed(BaseResponse<ArrayList<HistoryIntegralBean>> arrayListBaseResponse) {
               super.onSucceed(arrayListBaseResponse);
               mView.showIntrgalList(arrayListBaseResponse.getData());
           }
       }));

    }
}
