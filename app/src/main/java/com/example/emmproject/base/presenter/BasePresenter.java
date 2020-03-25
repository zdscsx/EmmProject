package com.example.emmproject.base.presenter;

import com.example.emmproject.base.view.AbstractView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {
    protected T mView;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
           mView=view;
    }

    @Override
    public void detachView() {
        mView=null;
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }

    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    protected void addSubscribe(Disposable disposable){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }






}
