package com.example.emmproject.core;

import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.BaseObserver;

/**
 * 说明： 继承BaseObserve 有错误提示和加载框
 * 作者：
 * 添加时间：
 */
public class TipObserver<T> extends BaseObserver<T> {

    public TipObserver(AbstractView mView, String mMessage) {
        super(mView, mMessage);
    }

    public TipObserver(AbstractView mView) {
        super(mView);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        view.cancelWaiting();

    }

    @Override
    protected void onStart() {
        super.onStart();
        view.showWaiting();

    }

    @Override
    public void onComplete() {
        super.onComplete();
        view.cancelWaiting();

    }

    @Override
    public void onFail(String cause) {
        super.onFail(cause);
        if(view!=null){
            view.showErrorMessage(cause);
        }
    }

}
