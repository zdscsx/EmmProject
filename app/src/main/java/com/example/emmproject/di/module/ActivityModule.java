package com.example.emmproject.di.module;

import com.example.emmproject.di.component.BaseActivityComponent;
import com.example.emmproject.ui.history.activity.OrderInfoActivity;
import com.example.emmproject.ui.mine.activity.ActivityActivity;
import com.example.emmproject.ui.mine.activity.ChangeUserInfoActivity;
import com.example.emmproject.ui.main.LoginActivity;
import com.example.emmproject.ui.main.MainActivity;
import com.example.emmproject.ui.mine.activity.CouponsActivity;
import com.example.emmproject.ui.mine.activity.UnAvailableCouponsActivity;
import com.example.emmproject.ui.order.activity.MapActivity;
import com.example.emmproject.ui.mine.activity.UserInfoActivity;
import com.example.emmproject.ui.main.VerifyCodeActivity;

import dagger.android.ContributesAndroidInjector;
import dagger.Module;

@Module(subcomponents = BaseActivityComponent.class)
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity contributeLoginActivityInjector();

    @ContributesAndroidInjector(modules = VerifyCodeModule.class)
    abstract VerifyCodeActivity contributeVerifyCodeInjector();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainInjector();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract MapActivity contributeMapInjector();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract UserInfoActivity contributeUserInfoInjector();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract ChangeUserInfoActivity contributeChangeInfoInjector ();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract CouponsActivity contributeCouponsActivityInjector();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract OrderInfoActivity contributeOrderinfoActivityInjector();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract ActivityActivity contributeActivityInjector();

    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract UnAvailableCouponsActivity contributeUnavailableInjector();


}
