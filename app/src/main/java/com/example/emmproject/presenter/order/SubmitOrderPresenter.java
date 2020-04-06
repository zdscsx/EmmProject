package com.example.emmproject.presenter.order;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.order.SubmitOrderContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.order.PayRequestBean;
import com.example.emmproject.core.bean.order.WechatPayBean;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;

import org.greenrobot.greendao.annotation.Index;

import javax.inject.Inject;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class SubmitOrderPresenter extends BasePresenter<SubmitOrderContract.View> implements SubmitOrderContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SubmitOrderPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }


    @Override
    public String getPhone() {
        return mDataManager.getUserPhone();
    }

    @Override
    public void startPayRequest(PayRequestBean payRequestBean) {
        LogUtils.logGson("pay");
        addSubscribe(mDataManager.payRequest(payRequestBean).compose(RxUtils.rxSchedulerHelper())
         .subscribeWith(new TipObserver<WechatPayBean>(mView){
             @Override
             public void onSucceed(BaseResponse<WechatPayBean> wechatPayBeanBaseResponse) {
                 super.onSucceed(wechatPayBeanBaseResponse);
                 LogUtils.logGson("pausucceed");
                 //mView.toPay(wechatPayBeanBaseResponse.getData());

             }

             @Override
             public void onFail(String cause) {
                 super.onFail(cause);
                 LogUtils.logGson("payfail");
             }

         }));

    }
}
