package com.example.emmproject.core.http;

import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.main.LoginBean;
import com.example.emmproject.core.bean.main.LoginByPasswordBean;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.core.bean.mine.ExchangeRequestBean;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.history.DataOrderHistory;
import com.example.emmproject.core.bean.main.RefreshTokenBean;
import com.example.emmproject.core.bean.mine.CouponsDataBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.core.bean.order.PayRequestBean;
import com.example.emmproject.core.bean.order.PrePayInfoBean;
import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.example.emmproject.core.bean.order.SubmitOrderBean;
import com.example.emmproject.core.bean.order.WechatPayBean;
import com.example.emmproject.utils.LogUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class HttpHelperImpl implements HttpHelper {


    EmmApis emmApis;

    @Inject
    public HttpHelperImpl(EmmApis emmApis) {
        this.emmApis = emmApis;
    }

    @Override
    public Observable<Response<BaseResponse<User>>> login(LoginBean loginBean) {
        return emmApis.login(loginBean.getPhone(),loginBean.getCode(),loginBean.getMessage(),"app");
    }

    @Override
    public Observable<Response<BaseResponse<User>>> loginByPassword(LoginByPasswordBean loginByPasswordBean) {
        return emmApis.loginByPassword(loginByPasswordBean,"app");
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
      return emmApis.getOrderHistory(token,pageNum,pageSize);
    }

    @Override
    public Observable<BaseResponse<RefreshTokenBean>> refreshToken(String refreshToken,String token) {
        return emmApis.refreshToken(refreshToken,token,token,"app");
    }

    @Override
    public Observable<BaseResponse<User>> changeInfo(String token, User user) {
        return emmApis.changeInfo(token,user);
    }

    @Override
    public Observable<BaseResponse<PrePayInfoBean>> getOrderInfo(String token, long timestamp, SubmitOrderBean submitOrderBean) {
        return emmApis.getOrderInfo(token,timestamp,submitOrderBean);    }

    @Override
    public Observable<BaseResponse> logout(String token, String phone) {
        return emmApis.logout(token,phone);
    }

    @Override
    public Observable<BaseResponse<WechatPayBean>> payRequest(String tokrn, PayRequestBean payRequestBean) {
        return emmApis.payRequest(tokrn,payRequestBean,System.currentTimeMillis());
    }

    public Observable<BaseResponse<ArrayList<HistoryIntegralBean>>> queryIntegralHistory(String token){
        return emmApis.queryIntegralHistory(token);
    }

   public Observable<BaseResponse<String>> exchangeCoupons(String header, ExchangeRequestBean exchangeRequestBean){
        return emmApis.exchangeCoupons(header,exchangeRequestBean);
    }
   public Observable<BaseResponse<ArrayList<CouponsBean>>> queryAllIntegralCoupons(String token){
        return emmApis.queryAllIntegralCoupons(token);
   }

  /*  @Override
    public Observable<Response<MarkLocationBean[]>> getResponse(String token) {
        return emmApis.getResponse(token);
    }*/
}
