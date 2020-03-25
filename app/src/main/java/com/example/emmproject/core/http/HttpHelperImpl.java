package com.example.emmproject.core.http;

import android.util.Log;

import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.LoginBean;
import com.example.emmproject.core.bean.LoginByPasswordBean;
import com.example.emmproject.core.bean.MarkLocationBean;
import com.example.emmproject.core.bean.history.DataOrderHistory;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.core.bean.main.RefreshTokenBean;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.core.bean.mine.CouponsDataBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.example.emmproject.utils.LogUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Part;

public class HttpHelperImpl implements HttpHelper {


    EmmApis emmApis;

    @Inject
    public HttpHelperImpl(EmmApis emmApis) {
        this.emmApis = emmApis;
    }

    @Override
    public Observable<Response<BaseResponse<User>>> login(LoginBean loginBean) {
        return emmApis.login(loginBean.getPhone(),loginBean.getCode(),loginBean.getMessage());
    }

    @Override
    public Observable<Response<BaseResponse<User>>> loginByPassword(LoginByPasswordBean loginByPasswordBean) {
        return emmApis.loginByPassword(loginByPasswordBean);
    }

    @Override
    public Observable<BaseResponse<String>> getVerifyCode(String phone) {
        return emmApis.getVerifyCode(phone);
    }

    @Override
    public Observable<BaseResponse<DataBean<MarkLocationBean>>> getMarkLocation(String token) {
        return emmApis.getMarkLocation(token);
    }

    @Override
    public Observable<BaseResponse<StoreFoodBean[]>> getStoreFood(String token, int storeId) {
        return emmApis.getStoreFood(token);
    }

    @Override
    public Observable<BaseResponse<String>> changePic(String token, MultipartBody.Part file) {
        return emmApis.changePic(token,file);
    }

    @Override
    public Observable<BaseResponse<IntegralBean>> getIntegral(String token) {
        return emmApis.getIntegral(token);
    }

    @Override
    public Observable<BaseResponse<CouponsDataBean>> getCupons(String token) {
        return emmApis.getCoupons(token);
    }

    @Override
    public Observable<BaseResponse<User>> getUser(String token, String phone) {
        return emmApis.getUser(token,phone);
    }

    @Override
    public Observable<BaseResponse<DataOrderHistory>> getOrderHistory(String token, int pageNum, int pageSize) {
                    LogUtils.logd(token+pageNum+pageSize);
      return emmApis.getOrderHistory(token,pageNum,pageSize);
    }

    @Override
    public Observable<BaseResponse<RefreshTokenBean>> refreshToken(String refreshToken,String token) {
          LogUtils.logd(refreshToken+"t     " +
                  token);
        return emmApis.refreshToken(refreshToken,token,token);
    }

    @Override
    public Observable<BaseResponse<User>> changeInfo(String token, User user) {
        return emmApis.changeInfo(token,user);
    }


  /*  @Override
    public Observable<Response<MarkLocationBean[]>> getResponse(String token) {
        return emmApis.getResponse(token);
    }*/
}
