package com.example.emmproject.core;

import android.util.Log;


import com.example.emmproject.app.Constants;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.utils.LogUtils;

import io.reactivex.observers.ResourceObserver;

public class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {

    public AbstractView view;
    public String mMessage;


    public BaseObserver(AbstractView mView, String mMessage) {
        this.mMessage=mMessage;
        this.view = mView;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public BaseObserver(AbstractView mView) {
        this.view = mView;
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
    }

    public void onFail(String cause){
        LogUtils.logd(cause);

    }

    public void onSucceed(BaseResponse<T> tBaseResponse){

    }

    @Override
    public void onError(Throwable e) {

        onFail(e.getCause()+"");
        LogUtils.loge(e);

    }

    @Override
    public void onComplete() {

    }
}
