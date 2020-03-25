package com.example.emmproject.di.module;

import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.db.DbHelper;
import com.example.emmproject.core.db.DbHelperImpl;
import com.example.emmproject.core.http.HttpHelper;
import com.example.emmproject.core.http.HttpHelperImpl;
import com.example.emmproject.core.prefs.PreferenceHelper;
import com.example.emmproject.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final EmmApplication application;

    public AppModule(EmmApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    EmmApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DbHelper provideDBHelper(DbHelperImpl realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbhelper, PreferenceHelper preferencesHelper) {
        return new DataManager(httpHelper,dbhelper,preferencesHelper);
    }

}
