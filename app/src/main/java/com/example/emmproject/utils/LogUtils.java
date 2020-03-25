package com.example.emmproject.utils;

import android.util.Log;

import com.google.gson.Gson;

public class LogUtils {

   public static void logd(String s){
        Log.d("LogUtils", "logd: "+s);
    }

   public static   void loge(Throwable e){
        Log.d("LogUtils", "loge:    cause:"+e.getCause()+ "  message"+e.getMessage());
    }

    public static <T> void logGson(T o){
              Log.d("LogUtils", "logGson:"+new Gson().toJson(o));

    }
}
