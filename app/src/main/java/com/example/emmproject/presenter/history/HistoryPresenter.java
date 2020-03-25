package com.example.emmproject.presenter.history;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.history.HistoryContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.utils.RxUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class HistoryPresenter extends BasePresenter<HistoryContract.View> implements HistoryContract.Presenter {

    private   DataManager mDataManager;

    @Inject
    public HistoryPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getHistoryList(int historyListType) {
        if (mDataManager.getUser()==null){  //判断有没有登录
            mView.showUnLogin();
        }
        else {
            addSubscribe( mDataManager.getOrderHistory(1,10,historyListType)
                    .compose(RxUtils.rxSchedulerHelper())
                    .subscribeWith(new BaseObserver<ArrayList<OrderHistoryBean>>(mView){
                        @Override
                        public void onSucceed(BaseResponse<ArrayList<OrderHistoryBean>> baseResponse) {
                            super.onSucceed(baseResponse);
                            if (baseResponse.getData().size()==0) {
                                mView.showEmpty();
                            }
                            else {
                                mView.showHistoryList(baseResponse.getData());
                            }
                        }
                    }));
        }
    }
}
