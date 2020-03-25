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

import javax.annotation.PostConstruct;

import io.reactivex.Observable;
import io.reactivex.annotations.SchedulerSupport;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EmmApis {
    static final String HOST="https://kpengidrive.com/";

    @FormUrlEncoded
    @POST("charging-order/api/user/login")
    Observable<Response<BaseResponse<User>>> login(@Field("phone") String phone,@Field("code") String code,@Header("sessionId") String message);

    @POST("charging-order/api/user/loginByPassword")
     Observable<Response<BaseResponse<User>>> loginByPassword(@Body LoginByPasswordBean loginByPasswordBean);

    @GET("charging-order/api/user/refreshCode/{phone}")
    Observable<BaseResponse<String>> getVerifyCode(@Path("phone") String phone);

    @GET("charging-order/api/charge/findAll")
    Observable<BaseResponse<DataBean<MarkLocationBean>>> getMarkLocation(@Header("token") String token);

    @GET("charging-order/api/charge/findAll")
    Observable<Response<MarkLocationBean[]>> getResponse(@Header("token") String token);

    @GET("charging-order/api/store/findAllStoreFood")
    Observable<BaseResponse<StoreFoodBean[]>> getStoreFood(@Header("token") String token);

    @Multipart
    @POST("charging-order/api/file/photo")
    Observable<BaseResponse<String>>  changePic(@Header("token") String token,@Part MultipartBody.Part file);

    @POST("charging-order/api/integralController/queryUserIntegral")
    Observable<BaseResponse<IntegralBean>> getIntegral(@Header("token") String token);

     @GET("charging-order/api/coupon/getUserCoupons")
    Observable<BaseResponse<CouponsDataBean>>  getCoupons(@Header("token")String token);

    @GET ("charging-order/api/user/select/{phone}")
    Observable<BaseResponse<User>> getUser(@Header("token") String token,@Path("phone") String phone);

    @GET("charging-order/api/order/findOrderList")
    Observable<BaseResponse<DataOrderHistory>> getOrderHistory(@Header("token") String token, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("charging-order/api/user/refreshToken")
    Observable<BaseResponse<RefreshTokenBean>> refreshToken(@Field("refresh_token") String refreshToken,@Header("token" )String token,@Field("token") String t );

    @POST("charging-order/api/user/update")
    Observable<BaseResponse<User>> changeInfo(@Header("token") String token,@Body User user);

}
