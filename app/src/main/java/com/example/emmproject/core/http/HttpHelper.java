package com.example.emmproject.core.http;

import com.example.emmproject.contract.order.SubmitOrderContract;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.main.LoginBean;
import com.example.emmproject.core.bean.main.LoginByPasswordBean;
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

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;

public interface HttpHelper {

    //验证码登录
     Observable<Response<BaseResponse<User>>> login( LoginBean loginBean);
     //密码登陆
     Observable<Response<BaseResponse<User>>> loginByPassword(LoginByPasswordBean loginByPasswordBean);
     //获取验证码
     Observable<BaseResponse<String>> getVerifyCode( String phone);
     //查询充电桩信息
     Observable<BaseResponse<DataBean<MarkLocationBean>>> getMarkLocation(String token);
     //查询充电桩中商家菜单
     Observable<BaseResponse<StoreFoodBean[]>> getStoreFood(String token, int chardId);

    // Observable<Response<MarkLocationBean[]>> getResponse(@Header("token") String token);

     Observable<BaseResponse<String>> changePic(String token, MultipartBody.Part file);
     //获取积分
     Observable<BaseResponse<IntegralBean>> getIntegral(String token);
      //获取优惠卷列表
     Observable<BaseResponse<CouponsDataBean>>  getCupons(String token);
     //获取用户信息
      Observable<BaseResponse<User>> getUser(String token,String phone);;
      //获取历史订单信息
      Observable<BaseResponse<DataOrderHistory>> getOrderHistory(String token, int pageNum, int pageSize);
      //刷新token
      Observable<BaseResponse<RefreshTokenBean>> refreshToken(String refreshToken,String token );

      Observable<BaseResponse<User>> changeInfo(String token,User user);
      //点餐结算
      Observable<BaseResponse<PrePayInfoBean>> getOrderInfo(String token, long timestamp, SubmitOrderBean submitOrderBean);

      Observable<BaseResponse> logout( String token,String phone);

      //支付
      Observable<BaseResponse<WechatPayBean>> payRequest(String tokrn, PayRequestBean payRequestBean);
         //获取历史积分记录
     Observable<BaseResponse<ArrayList<HistoryIntegralBean>>> queryIntegralHistory(String token);



}
