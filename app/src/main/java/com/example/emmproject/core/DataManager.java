package com.example.emmproject.core;

import android.util.Log;

import com.example.emmproject.app.Constants;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.ElemeGroupedItem;
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
import com.example.emmproject.core.db.DbHelper;
import com.example.emmproject.core.http.HttpHelper;
import com.example.emmproject.core.prefs.PreferenceHelper;
import com.example.emmproject.utils.CommonUtils;
import com.example.emmproject.utils.LogUtils;
import com.google.gson.Gson;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Multipart;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataManager {

    private HttpHelper mHttpHelper;
    private DbHelper mDbhelper;
    private PreferenceHelper mPreferenceHelper;
    @Inject
    public DataManager(HttpHelper httpHelper, DbHelper dbhelper, PreferenceHelper preferencesHelper) {
        mHttpHelper=httpHelper;
        mDbhelper=dbhelper;
        mPreferenceHelper=preferencesHelper;
    }

    public Observable<Response<BaseResponse<User>>> login(LoginBean loginBean) {

        return mHttpHelper.login(loginBean);
    }


    public Observable<Response<BaseResponse<User>>> loginByPassword(LoginByPasswordBean loginByPasswordBean) {
        return mHttpHelper.loginByPassword(loginByPasswordBean);

    }

    public Observable<BaseResponse<String>> getVerifyCode(String phone){
        return mHttpHelper.getVerifyCode(phone);

    }

    public Observable<BaseResponse<DataBean<MarkLocationBean>>> getMarkLocation(){
        return mHttpHelper.getMarkLocation(getToken());
    }

    public Observable<BaseResponse<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>>> getStoreFood(int chardId){

        return   mHttpHelper.getStoreFood(getToken(),chardId).map(new Function<BaseResponse<StoreFoodBean[]>, BaseResponse<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>>>() {
            //把后台数据转成双recyclerview框架要用的格式
            @Override
            public BaseResponse<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>> apply(BaseResponse<StoreFoodBean[]> dataStoreFoodBeanBaseResponse) throws Exception {
                BaseResponse<ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>> baseResponse=new BaseResponse<>(dataStoreFoodBeanBaseResponse);
                ArrayList <BaseGroupedItem<ElemeGroupedItem.ItemInfo>> arrayList=new ArrayList<>();
                for (StoreFoodBean storeFoodBean:dataStoreFoodBeanBaseResponse.getData()){
                    arrayList.add( new ElemeGroupedItem(true,storeFoodBean.getStoreInfo().getName()));  //商家标题
                    for (StoreFoodBean.FoodListBean food:storeFoodBean.getFoodList()){ //商品
                        ElemeGroupedItem elemeGroupedItem
                                =new ElemeGroupedItem(new ElemeGroupedItem.ItemInfo(food.getName(),storeFoodBean.getStoreInfo().getName(),food));
                        elemeGroupedItem.setStoreInfoBean(storeFoodBean.getStoreInfo());
                        elemeGroupedItem.setStoreId(storeFoodBean.getStoreId());
                        arrayList.add(elemeGroupedItem);
                    }
                }
                baseResponse.setData(arrayList);
                return baseResponse;
            }
        });
    };

    public void getResponse(){ //debug用的获取请求body
        Log.d(TAG, "getResponse: a");
        OkHttpClient okHttpClient=new OkHttpClient
                .Builder().build();

        String url = "https://kpengidrive.com/charging-order/api/order/findOrderList?pageNum=1&pageSize=10";
        final Request request=new Request.Builder().url(url).
                addHeader("token",getToken()).build();
        final Call call=okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        Log.d(TAG, "onResponse "+e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        Log.d(TAG, "onResponse: "+response.body().string());
                    }
                });
            }
        }).start();
    }

    public Observable<BaseResponse<String>> changePic( LocalMedia media){
        File file=new File(media.getPath());
        RequestBody requestBody=RequestBody.create(CommonUtils.guessMimeType(file.getPath()),file);
        MultipartBody.Part part=MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        return mHttpHelper.changePic(getToken(),part);
    }

    Observable<BaseResponse<IntegralBean>> getIntegral(String token){
        return mHttpHelper.getIntegral(token);
    }

    Observable<BaseResponse<ArrayList<CouponsBean>>>  getCupons(){
        return mHttpHelper.getCupons(getToken()).map(new Function<BaseResponse<CouponsDataBean>, BaseResponse<ArrayList<CouponsBean>>>() {
            @Override
            public BaseResponse<ArrayList<CouponsBean>> apply(BaseResponse<CouponsDataBean> couponsBeanBaseResponse) throws Exception {
                ArrayList<CouponsBean > couponsBeans=new ArrayList<>(Arrays.asList(couponsBeanBaseResponse.getData().getAvailableCoupons()));
                BaseResponse<ArrayList<CouponsBean>> baseResponse=new BaseResponse<>(couponsBeanBaseResponse);
                baseResponse.setData(couponsBeans);
                return baseResponse ;
            }
        });
    }

    Observable<BaseResponse<ArrayList<CouponsBean>>>  getUnAvailableCupons(){
        return mHttpHelper.getCupons(getToken()).map(new Function<BaseResponse<CouponsDataBean>, BaseResponse<ArrayList<CouponsBean>>>() {
            @Override
            public BaseResponse<ArrayList<CouponsBean>> apply(BaseResponse<CouponsDataBean> couponsBeanBaseResponse) throws Exception {
                ArrayList<CouponsBean > couponsBeans=new ArrayList<>(Arrays.asList(couponsBeanBaseResponse.getData().getUnAvailableCoupons()));
                BaseResponse<ArrayList<CouponsBean>> baseResponse=new BaseResponse<>(couponsBeanBaseResponse);
                baseResponse.setData(couponsBeans);
                return baseResponse ;
            }
        });
    }



    public Observable<BaseResponse<User>> getUserFormHttp() {
        return mHttpHelper.getUser(getToken(),getUser().getPhone());
    }

    public Observable<BaseResponse<ArrayList<OrderHistoryBean>>> getOrderHistory(int pageNum, int pageSize,int businessStatus){
        getResponse();
        LogUtils.logd(getToken());
        return mHttpHelper.getOrderHistory(getToken(),pageNum,pageSize)
                .map(new Function<BaseResponse<DataOrderHistory>, BaseResponse<ArrayList<OrderHistoryBean>>>() {
                    @Override
                    public BaseResponse<ArrayList<OrderHistoryBean>> apply(BaseResponse<DataOrderHistory> baseResponse) throws Exception {
                        BaseResponse<ArrayList<OrderHistoryBean>> response=new BaseResponse(baseResponse);
                        ArrayList<OrderHistoryBean> orderHistoryBeans=new ArrayList<>();
                        if (baseResponse.getData()!=null)
                        for (OrderHistoryBean historyBean:baseResponse.getData().getOrderList()){
                            switch (businessStatus){
                                case Constants
                                        .ORDER_STATUS_ALL:orderHistoryBeans.add(historyBean);
                                    break;
                                case Constants.ORDER_STATUS_BOOK:
                                    if (historyBean.getOrderInfo().getBusinessStatus()==Constants.ORDER_STATUS_WAIT)
                                        orderHistoryBeans.add(historyBean);
                                    break;
                                case Constants.ORDER_STATUS_WAITTAKE:
                                    if (historyBean.getOrderInfo().getBusinessStatus()==Constants.ORDER_STATUS_PYA)
                                        orderHistoryBeans.add(historyBean);
                                    break;
                            }

                        }
                        response.setData(orderHistoryBeans);
                        return response;
                    }
                });

    }

   public Observable<BaseResponse<RefreshTokenBean>> refreshToken(){
        return mHttpHelper.refreshToken(getrRefreshToken(),getToken());
    }

   public Observable<BaseResponse<User>> changeInfo(User user){

        return mHttpHelper.changeInfo(getToken(),user);
    }



    //sharepreference

    public void saveTooken(String token){
        mPreferenceHelper.setToken(token);
    }

    String getToken(){
        return mPreferenceHelper.getToken();
    }

    public void setUesrPhone(String phone){
        mPreferenceHelper.setUesrPhone(phone);
    }

   public String getUserPhone(){
        return mPreferenceHelper.getUserPhone();
    }

    void setId(Integer id){
        mPreferenceHelper.setId(id);
    }

    Integer getId(){
        return mPreferenceHelper.getId();
    }



    //DbHelper
    public void saveUser(User user) {
        mDbhelper.saveUser(user);
    }


    public User getUser() {
        return mDbhelper.getUser(getUserPhone());
    }

    public void savaRefreshToken(String refreshToken){
        mPreferenceHelper.saverefreshToken(refreshToken);
    }

    public String getrRefreshToken(){
        return mPreferenceHelper.getrefreshToken();
    }
}
