package com.example.emmproject.core;

import android.util.Log;


import com.example.emmproject.app.Constants;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.utils.LogUtils;

import io.reactivex.observers.ResourceObserver;

public class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {

    private AbstractView mView;
    private String mMessage;


    public BaseObserver(AbstractView mView, String mMessage) {
        this.mMessage=mMessage;
        this.mView = mView;
    }

    @Override
    protected void onStart() {
        mView.showWaiting();
        super.onStart();
    }

    public BaseObserver(AbstractView mView) {
        this.mView = mView;
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        LogUtils.<BaseResponse<T>>logGson(tBaseResponse);
        if (tBaseResponse.getCode()== Constants.STSTUS_SUCCESS) {
            onSucceed(tBaseResponse);
        }
        else {
            onFail(tBaseResponse.getMessage());
        }
        mView.cancelWaiting();
    }

    public void onFail(String cause){
        LogUtils.logd(cause);

    }

    public void onSucceed(BaseResponse<T> tBaseResponse){

    }

    @Override
    public void onError(Throwable e) {
        mView.cancelWaiting();
        LogUtils.loge(e);
      if(mView!=null){
            mView.showErrorMessage("message: "+e.getMessage()+"  cause: "+e.getCause());
        }

    }

    @Override
    public void onComplete() {

    }
}
