package com.example.emmproject.core;

import android.text.TextUtils;
import android.util.Log;


import com.example.emmproject.R;
import com.example.emmproject.app.Constants;
import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.utils.LogUtils;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

public class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {

    public AbstractView view;
    public String mMessage;
    private boolean showError;


    public BaseObserver(AbstractView mView, String mMessage,boolean showError) {
        this.mMessage=mMessage;
        this.view = mView;
        this.showError=showError;
    }


    public BaseObserver(AbstractView mView, String mMessage) {
        this.mMessage=mMessage;
        this.view = mView;
    }

    public BaseObserver(AbstractView mView, boolean showError) {
        this.view=mView;
        this.showError = showError;
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
            onFail(tBaseResponse.getMessage(),tBaseResponse.getCode());
        }
    }

    public void onFail(String cause){

    }

    public void onFail(String cause,int code){
        LogUtils.logd(cause+"code "+code);
        onFail(cause);

    }

    public void onSucceed(BaseResponse<T> tBaseResponse){

    }

    @Override
    public void onError(Throwable e) {
        if (mMessage != null && !TextUtils.isEmpty(mMessage)) {
            view.showToast(mMessage);
        } else if (e instanceof HttpException) {
            view.showToast(EmmApplication.getInstance().getString(R.string.http_error));
        }
      if (showError) {
            view.showError();
        }
      onFail(e.getCause()+"",444);
        LogUtils.loge(e);

    }

    @Override
    public void onComplete() {

    }
}
