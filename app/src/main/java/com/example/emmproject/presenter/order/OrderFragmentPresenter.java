package com.example.emmproject.presenter.order;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.order.ElemeGroupedItem;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.order.PrePayInfoBean;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.tencent.map.geolocation.TencentLocationUtils;

import java.util.ArrayList;

import javax.inject.Inject;

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
                        (new TipObserver<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>>(mView){
                             @Override
                             public void onSucceed(BaseResponse<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>> arrayListBaseResponse) {
                                 super.onSucceed(arrayListBaseResponse);
                                 mView.showProduct(arrayListBaseResponse.getData());
                             }
                         }
                        ));
    }

    @Override
    public void submitOrder(int chargeId,ArrayList<ShopCardFoodBean> shopCardFoodBeans) {
        if (mDataManager.getUser()==null){ //没有登陆就先登陆
            mView.showToast("请先登陆");
            mView.toLogin();
            return;
        }

        addSubscribe(mDataManager.getOrderInfo(chargeId,shopCardFoodBeans)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver<PrePayInfoBean>(mView){
                                   @Override
                                   public void onSucceed(BaseResponse<PrePayInfoBean> objectBaseResponse) {
                                       super.onSucceed(objectBaseResponse);
                                       mView.toSubmitActivity(objectBaseResponse.getData());
                                   }

                                   @Override
                                   public void onFail(String cause) {
                                       super.onFail(cause);
                                   }
                               }


                )
        );
    }

    @Override
    public void getMarkLocation() {
        addSubscribe(mDataManager.getMarkLocation().compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver<DataBean<MarkLocationBean>>(mView,true)
                               {@Override
                                   public void onSucceed(BaseResponse<DataBean<MarkLocationBean>> dataBeanBaseResponse) {
                                       super.onSucceed(dataBeanBaseResponse);
                                       MarkLocationBean[] markLocationBeans=dataBeanBaseResponse.getData().getArray();
                                       mView.setMarkLocation(markLocationBeans[0]);
                               }}
                ));
    }


}
