package com.example.emmproject.presenter.order;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.order.MapContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.utils.RxUtils;

import javax.inject.Inject;

public class MapPresenter extends BasePresenter<MapContract.View> implements MapContract.Presenter {

    DataManager mDataManager;

    @Inject
    public MapPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getMarkLocation() {
        addSubscribe(mDataManager.getMarkLocation().compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver<DataBean<MarkLocationBean>>(mView) {
            @Override
            public void onSucceed(BaseResponse<DataBean<MarkLocationBean>> dataBeanBaseResponse) {
                super.onSucceed(dataBeanBaseResponse);
                mView.setMarketList(dataBeanBaseResponse.getData().getArray());
            }
        }
        ));
    }
}
