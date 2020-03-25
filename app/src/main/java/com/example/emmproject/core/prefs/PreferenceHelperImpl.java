package com.example.emmproject.core.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.emmproject.app.EmmApplication;

import javax.inject.Inject;

public class PreferenceHelperImpl implements PreferenceHelper {
   private SharedPreferences mSharedPreferences;

    @Inject
    public PreferenceHelperImpl() {
        mSharedPreferences=EmmApplication.getInstance().getSharedPreferences("EmmProject", Context.MODE_PRIVATE);
    }


    @Override
    public void setToken(String token) {
        mSharedPreferences.edit().putString("token",token).apply();
    }

    @Override
    public String getToken() {
        return mSharedPreferences.getString("token","");
    }

    @Override
    public void setUesrPhone(String uesrname) {

        mSharedPreferences.edit().putString("phone",uesrname).apply();
    }

    @Override
    public String getUserPhone() {
        return mSharedPreferences.getString("phone","");
    }

    @Override
    public void setId(Integer id) {
        mSharedPreferences.edit().putInt("id",id).apply();
    }

    @Override
    public Integer getId() {
        return mSharedPreferences.getInt("id",0);
    }

    @Override
    public void saverefreshToken(String refreshToken) {
     mSharedPreferences.edit().putString("refreshToken",refreshToken).apply();
    }

    @Override
    public String getrefreshToken() {
        return mSharedPreferences.getString("refreshToken","");
    }


}
