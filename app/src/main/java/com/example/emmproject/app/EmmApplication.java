package com.example.emmproject.app;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import androidx.multidex.MultiDex;

import com.example.emmproject.core.bean.mine.DaoMaster;
import com.example.emmproject.core.bean.mine.DaoSession;
import com.example.emmproject.core.http.EmmApis;
import com.example.emmproject.di.component.AppComponent;
import com.example.emmproject.di.component.DaggerAppComponent;
import com.example.emmproject.di.module.AppModule;
import com.example.emmproject.di.module.HttpModule;

import org.greenrobot.greendao.AbstractDaoMaster;

import javax.inject.Inject;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class EmmApplication extends Application implements HasActivityInjector
{
    static EmmApplication instance;
    static DaoSession mDaoSession;
    static   AppComponent appComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        appComponent=DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();
        appComponent.inject(this);
        initGreenDao();
        MultiDex.install(this);

    }

    public static AppComponent getComponent(){
        return appComponent;
    }


    public static EmmApplication getInstance() {
        return instance;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(this,Constants.DB_NAME);
        SQLiteDatabase sqLiteDatabase=devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(sqLiteDatabase);
        mDaoSession=daoMaster.newSession();
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
