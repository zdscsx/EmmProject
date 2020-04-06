package com.example.emmproject.presenter.main;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.main.MainContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.main.RefreshTokenBean;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.tencent.map.geolocation.TencentLocationUtils;
import com.tencent.mapsdk.raster.model.LatLng;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    DataManager mDataManager;

    private LatLng mLatLng;
    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getMarkLocation(LatLng latLng) {
        addSubscribe(mDataManager.getMarkLocation().compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<DataBean<MarkLocationBean>>(mView)
                               {
                                   @Override
                                   public void onSucceed(BaseResponse<DataBean<MarkLocationBean>> dataBeanBaseResponse) {
                                       super.onSucceed(dataBeanBaseResponse);
                                       MarkLocationBean[] markLocationBeans=dataBeanBaseResponse.getData().getArray();
                                       try {
                                           LogUtils.logd(latLng.toString());

                                           if (latLng==null){ //没有定位成功 直接显示一个地址
                                               mView.showLocation(markLocationBeans[0]);
                                           }
                                           else { //找到最近位置
                                               MarkLocationBean minLocation=null;
                                               double minDistance=TencentLocationUtils.distanceBetween(latLng.getLatitude(),latLng.getLongitude(),
                                                       Double.parseDouble(markLocationBeans[0].getLat()),Double.parseDouble(markLocationBeans[0].getLng()));
                                               for (MarkLocationBean location:markLocationBeans){
                                                   double distance=TencentLocationUtils.distanceBetween(latLng.getLatitude(),latLng.getLongitude(),
                                                           Double.parseDouble(location.getLat()),Double.parseDouble(location.getLng()));
                                                   if (minDistance>distance){
                                                       minLocation=location;
                                                       minDistance=distance;
                                                   }
                                               }
                                               minLocation.setDistance((float)Math.round(minDistance/1000*100)/100);
                                               LogUtils.logd(minLocation.getDistance()+" ");
                                               mView.showLocation(minLocation);
                                           }
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                           LogUtils.loge(e);
                                       }
                                   }
                               }
                ));
    }

    @Override
    public void refreshToken() {
        refersh();
        addSubscribe( Observable.interval(10, TimeUnit.MINUTES)//定时刷新
                .subscribe(new Consumer<Long>() {
                               @Override
                               public void accept(Long aLong) throws Exception {
                                   refersh();
                               }
                           }));


    }

    void refersh(){
        addSubscribe(mDataManager.refreshToken().compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<RefreshTokenBean>(mView) {
                    @Override
                    public void onSucceed(BaseResponse<RefreshTokenBean> refreshTokenBeanBaseResponse) {
                        super.onSucceed(refreshTokenBeanBaseResponse);
                        LogUtils.logd("刷新token");
                        mDataManager.saveTooken(refreshTokenBeanBaseResponse.getData().getToken());
                        mDataManager.savaRefreshToken(refreshTokenBeanBaseResponse.getData().getRefresh_token());
                    }
                })
        );
    }


}
