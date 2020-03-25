package com.example.emmproject.core.http;

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

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Part;

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



}
