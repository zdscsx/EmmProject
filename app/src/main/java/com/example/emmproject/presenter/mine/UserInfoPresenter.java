package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.UserInfoContract;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.core.bean.mine.ExchangeRequestBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.example.emmproject.utils.RxUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.functions.Predicate;

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
    public void queryIntrgalHistory(int state) {
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
               ArrayList<HistoryIntegralBean> integralBeans=new ArrayList<>();
               for (HistoryIntegralBean integralBean:arrayListBaseResponse.getData()){
                   if (integralBean.getStatus()==state)
                       integralBeans.add(integralBean);
               }
               mView.showIntrgalList(integralBeans);
           }
       }));

    }

    @Override
    public void exchangeCoupons(ExchangeRequestBean exchangeRequestBean) {
        addSubscribe(mDataManager.exchangeCoupons(exchangeRequestBean)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new TipObserver(mView){
            @Override
            public void onSucceed(BaseResponse baseResponse) {
                super.onSucceed(baseResponse);
                mView.showToast("兑换成功");
            }
        }));
    }

    @Override
    public void queryIntegralCoupons() {
        addSubscribe(mDataManager.queryAllIntegralCoupons()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver<ArrayList<CouponsBean>>(mView){
                    @Override
                    public void onSucceed(BaseResponse<ArrayList<CouponsBean>> baseResponse) {
                        super.onSucceed(baseResponse);
                        for (CouponsBean couponsBean:baseResponse.getData()) {
                            ExchangeRequestBean exchangeRequestBean=new ExchangeRequestBean();
                            exchangeRequestBean.setCouponId(couponsBean.getCouponId());
                            exchangeRequestBean.setQuantity(1);
                            exchangeCoupons(exchangeRequestBean);
                        }
                        mView.showToast("查询成功");

                    }
                }));
    }
}
