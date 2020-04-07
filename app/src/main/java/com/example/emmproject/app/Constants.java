package com.example.emmproject.app;

import android.os.AsyncTask;

import com.example.emmproject.core.http.EmmApis;

import java.io.File;

public class Constants {
 public static final String DB_NAME="EMM.db";
 public static final String PATH_CACHE= EmmApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
 public static final int STSTUS_SUCCESS=1000;


 public static final int COUPONTYPE_REDUCE=1; //满减卷
 public static final int COUPONSTYPE_CASH=2;
 public static final int COUPONSTYPE_FOOD=3;
//这个是订单的四种状态
 public static final int ORDER_STATUS_CANCEL=0;
 public static final int ORDER_STATUS_WAITPAY=1;
 public static final int ORDER_STATUS_WAITAKE=2;
 public static final int ORDER_STATUS_FINISH=3;

//这个是客户端的tab 状态对应的int
 public static final int ORDER_CLIENT_STATUS_ALL=0;
 public static final int ORDER_CLIENT__STATUS_WAITTAKE=1;
 public static final int ORDER_CLIENT_STATUS_FINISH=2;
// AsyncTask

 public static final String APP_ID = "wx88888888";
//圆角度数
 public static final  int ROUND_CORNERS=20;
 //积分状态变化
 public  static final int INTEGRAL_ADD=1;
 public static final int  INTEGRAL_REDUCE=2;

 //性别
 public static final int SEX_MAN=0;
 public static final int SEX_WOMWN=1;


}
