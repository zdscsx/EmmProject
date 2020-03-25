package com.example.emmproject.di.component;

import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.di.module.ActivityModule;
import com.example.emmproject.di.module.AppModule;
import com.example.emmproject.di.module.HttpModule;
import com.example.emmproject.ui.history.fragment.HistoryFragment;
import com.example.emmproject.ui.mine.MineFragment;
import com.example.emmproject.ui.order.OrderFragment;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
@Singleton
@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
        ActivityModule.class, AppModule.class, HttpModule.class})
public interface AppComponent  {

    void inject(EmmApplication application);

    void inject(OrderFragment orderFragment);


    void inject(MineFragment mineFragment);

    void inject(HistoryFragment historyFragment);

}
