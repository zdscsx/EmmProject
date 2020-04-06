package com.example.emmproject.core;

import android.util.Log;

import com.example.emmproject.app.Constants;
import com.example.emmproject.core.bean.DataBean;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.order.ElemeGroupedItem;
import com.example.emmproject.core.bean.main.LoginBean;
import com.example.emmproject.core.bean.main.LoginByPasswordBean;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.history.DataOrderHistory;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.core.bean.main.RefreshTokenBean;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.core.bean.mine.CouponsDataBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.core.bean.order.PayRequestBean;
import com.example.emmproject.core.bean.order.PrePayInfoBean;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.example.emmproject.core.bean.order.SubmitOrderBean;
import com.example.emmproject.core.bean.order.WechatPayBean;
import com.example.emmproject.core.db.DbHelper;
import com.example.emmproject.core.http.HttpHelper;
import com.example.emmproject.core.prefs.PreferenceHelper;
import com.example.emmproject.utils.CommonUtils;
import com.example.emmproject.utils.LogUtils;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Response;

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
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        Log.d(TAG, "onResponse: "+Thread.currentThread());
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

   public Observable<BaseResponse<IntegralBean>> getIntegral(){
        return mHttpHelper.getIntegral(getToken());
    }

   public Observable<BaseResponse<ArrayList<CouponsBean>>>  getCupons(){
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

   public Observable<BaseResponse<ArrayList<CouponsBean>>>  getUnAvailableCupons(){
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
                                    if (historyBean.getOrderInfo().getBusinessStatus()==Constants.ORDER_STATUS_WAITPAY)
                                        orderHistoryBeans.add(historyBean);
                                    break;
                                case Constants.ORDER_STATUS_WAITTAKE:
                                    if (historyBean.getOrderInfo().getBusinessStatus()==Constants.ORDER_STATUS_WAITAKE)
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

    public Observable<BaseResponse<PrePayInfoBean>> getOrderInfo(int chargeId, ArrayList<ShopCardFoodBean> foodBeans){
        ArrayList<SubmitOrderBean.FoodOptionListBean> foodOptionListBeans=new ArrayList<>();
      for (ShopCardFoodBean foodBean: foodBeans){
          SubmitOrderBean.FoodOptionListBean optionListBean;
          String optionCode=null;
          if (foodBean.getSelectionsBeans()!=null){
               optionCode=1+"="+foodBean.getSelectionsBeans().getSelectId();
          }
          optionListBean=new SubmitOrderBean.FoodOptionListBean(foodBean.getFoodListBean().getFoodId(),foodBean.getQuantity(),optionCode);
          foodOptionListBeans.add(optionListBean);

      }
        SubmitOrderBean submitOrderBean=new SubmitOrderBean(chargeId,foodOptionListBeans);

        return mHttpHelper.getOrderInfo(getToken(),System.currentTimeMillis(),submitOrderBean);
    }

    public Observable<BaseResponse> logout(){
        return mHttpHelper.logout(getToken(),getUserPhone());
    }

   public Observable<BaseResponse<WechatPayBean>> payRequest( PayRequestBean payRequestBean){
        return mHttpHelper.payRequest(getToken(),payRequestBean);
    }

   public Observable<BaseResponse<ArrayList<HistoryIntegralBean>>> queryIntegralHistory(){
        return mHttpHelper.queryIntegralHistory(getToken());
    }

    //sharepreference

    public void saveTooken(String token){
        mPreferenceHelper.setToken(token);
    }

    String getToken(){
        LogUtils.logd(mPreferenceHelper.getToken());
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

    public void cleanData(){
        mDbhelper.cleaData();
    }
}
