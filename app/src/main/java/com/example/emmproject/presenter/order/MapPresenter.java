package com.example.emmproject.presenter.order;

import android.util.Log;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.order.MapContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.MarkLocationBean;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapPresenter extends BasePresenter<MapContract.View> implements MapContract.Presenter {

    DataManager mDataManager;

    @Inject
    public MapPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getMarkLocation() {
        addSubscribe(mDataManager.getMarkLocation().compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<DataBean<MarkLocationBean>>(mView) {
            @Override
            public void onSucceed(BaseResponse<DataBean<MarkLocationBean>> dataBeanBaseResponse) {
                super.onSucceed(dataBeanBaseResponse);
                super.onNext(dataBeanBaseResponse);
                mView.setMarketList(dataBeanBaseResponse.getData().getArray());
            }
        }
        ));
    }
}
