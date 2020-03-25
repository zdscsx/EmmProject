package com.example.emmproject.base.presenter;

import com.example.emmproject.base.view.AbstractView;
import io.reactivex.disposables.Disposable;

public interface  AbstractPresenter <T extends AbstractView> {

    void attachView(T view);

    void detachView();

    void addRxBindingSubscribe(Disposable disposable);




}
