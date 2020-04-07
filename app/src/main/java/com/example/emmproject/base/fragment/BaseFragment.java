package com.example.emmproject.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.utils.CommonUtils;
import com.example.emmproject.utils.LogUtils;
import com.kongzue.dialog.v3.WaitDialog;

import javax.inject.Inject;

public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractFragment implements AbstractView {
    @Inject
    protected T mPresenter;

    FragmentActivity mFragmentActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mFragmentActivity=(FragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void showToast(String message) {  CommonUtils.showMessage(getActivity(), message);
    }

   abstract public void inject();

    public void showSnackBar(String message) {
        CommonUtils.showSnackMessage(getActivity(), message);
    }

    public void showErrorMessage(String message){
        showToast(message);
    }


    public  void  showWaiting(){
        WaitDialog.show((AppCompatActivity) getActivity(), "请稍候...").setCancelable(true);
    }

    public void cancelWaiting(){
        WaitDialog.dismiss();
    }
    /**
     * showNormal
     */
    public void showNormal(){}

    /**
     * Show error
     */
    public void showError(){}

    /**
     * Show loading
     */
}
