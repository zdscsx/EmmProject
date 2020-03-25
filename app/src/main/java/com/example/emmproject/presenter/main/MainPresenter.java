package com.example.emmproject.presenter.main;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.main.MainContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.bean.main.RefreshTokenBean;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void refreshToken() {

            addSubscribe(mDataManager.refreshToken().compose(RxUtils.rxSchedulerHelper())
                    .subscribeWith(new BaseObserver<RefreshTokenBean>(mView){
                        @Override
                        public void onSucceed(BaseResponse<RefreshTokenBean> refreshTokenBeanBaseResponse) {
                            super.onSucceed(refreshTokenBeanBaseResponse);
                            mDataManager.saveTooken(refreshTokenBeanBaseResponse.getData().getToken());
                            mDataManager.savaRefreshToken(refreshTokenBeanBaseResponse.getData().getRefresh_token());
                        }
                    })
            );

    }
}
