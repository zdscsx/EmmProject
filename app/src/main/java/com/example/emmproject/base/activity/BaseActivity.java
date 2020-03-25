package com.example.emmproject.base.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.emmproject.R;
import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.utils.CommonUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.kongzue.dialog.v3.WaitDialog;

import javax.inject.Inject;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;


public  abstract  class BaseActivity <T extends AbstractPresenter> extends AbstractActivity implements AbstractView,
        HasSupportFragmentInjector {
    @Inject
    protected  T mPresenter ;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       AndroidInjection.inject(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.colorPrimary) .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .init();
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onViewCreated() {
       if (mPresenter!=null)
           mPresenter.attachView(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
            mPresenter=null;
        }
    }


    public void showToast(String message) {  CommonUtils.showMessage(this, message);

    }

    public  void  showWaiting(){
        WaitDialog.show(this, "请稍候...").setCancelable(true);
    }

    public void cancelWaiting(){
        WaitDialog.dismiss();
    }

    public void showSnackBar(String message) {
        CommonUtils.showSnackMessage(this, message);
    }

    public void showErrorMessage(String errorMessage){
        showToast(errorMessage);
    }

}
