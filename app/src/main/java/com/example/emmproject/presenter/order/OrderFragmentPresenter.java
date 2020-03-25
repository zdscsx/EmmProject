package com.example.emmproject.presenter.order;

import android.util.Log;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.example.emmproject.core.bean.MarkLocationBean;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.google.gson.Gson;
import com.kunminx.linkage.bean.BaseGroupedItem;

import java.util.ArrayList;

import javax.inject.Inject;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OrderFragmentPresenter extends BasePresenter<OrderFragmentContract.View> implements OrderFragmentContract.Presenter {
    DataManager mDataManager;

    @Inject
    public OrderFragmentPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getProduct(int storeId) {

        addSubscribe(mDataManager.getStoreFood(storeId).
                compose(RxUtils.rxSchedulerHelper())
                .subscribeWith
                        (new BaseObserver<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>>(mView){
                             @Override
                             public void onSucceed(BaseResponse<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>> arrayListBaseResponse) {
                                 super.onSucceed(arrayListBaseResponse);
                                 mView.showProduct(arrayListBaseResponse.getData());
                             }
                         }
                         ));
    }
}
