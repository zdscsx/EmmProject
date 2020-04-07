package com.example.emmproject.ui.order.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.app.Constants;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.order.SubmitOrderContract;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.order.PayRequestBean;
import com.example.emmproject.core.bean.order.PrePayInfoBean;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.core.bean.order.WechatPayBean;
import com.example.emmproject.presenter.order.SubmitOrderPresenter;
import com.example.emmproject.ui.order.adapter.SimpleFoodAdapter;
import com.example.emmproject.widget.datepicker.CustomDatePicker;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class SubmitOrderActivity extends BaseActivity<SubmitOrderPresenter> implements SubmitOrderContract.View {

    @BindView(R.id.rv_submit_list)
    RecyclerView rvFoodList;
    @BindView(R.id.tv_submit_actualcount)
    TextView tvActualCount;
    @BindView(R.id.tv_submit_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_submit_coupon)
    TextView tvCoupon;
    @BindView(R.id.tv_submit_location)
    TextView tvLocation;
    @BindView(R.id.tv_submit_name)
    TextView tvName;
    @BindView(R.id.tv_submit_phone)
    TextView tvPhone;
    @BindView(R.id.tv_submit_remark)
    TextView tvRemark;
    @BindView(R.id.tv_submit_total)
    TextView tvTotal;
    @BindView(R.id.tv_submit_time)
    TextView tvTime;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit_all)
    TextView tvAll;


    private PrePayInfoBean mPrePayInfoBean;
    private float actualPrice=0;
    private float totalPrice=0;
    private String discountPrice;
    private int quantity=0;
    private String takeFoodTime;
    private CouponsBean mCouponsBean;
    private String mNote="早点睡";
    private SimpleFoodAdapter mSubmitFoodAdapter;
    private MarkLocationBean mMarkLocationBean;
    private IWXAPI api;
    SimpleDateFormat hourSdf;
    List<Integer> couponSelect;
    @OnClick({R.id.ibt_toolbar_back,R.id.tv_submit_time,R.id.bt_submit_pay})
    void onClick(View view){
        switch (view.getId()){
            case R.id.ibt_toolbar_back:finish();
            break;
            case R.id.tv_submit_time:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                String now = sdf.format(new Date());
                String last=now.substring(0,11)+mMarkLocationBean.getWorkEndTime();

                CustomDatePicker customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                    @Override
                    public void onTimeSelected(long timestamp) {
                        takeFoodTime=hourSdf.format(new Date(timestamp));
                        tvTime.setText(takeFoodTime);
                    }
                }, now, last); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
                customDatePicker1.setCanShowPreciseTime(true);
                customDatePicker1.onlyShowPreciseTime();
                customDatePicker1.setScrollLoop(false); // 不允许循环滚动
                customDatePicker1.show(now);
                break;
            case R.id.bt_submit_pay:
                PayRequestBean payRequestBean=new PayRequestBean(mPrePayInfoBean.getPreOrderId(),takeFoodTime,mNote, 1,totalPrice+"0",couponSelect);
                mPresenter.startPayRequest(payRequestBean);


        }
    }
    public static void startActivity(Context context, PrePayInfoBean prePayInfoBean, MarkLocationBean markLocationBean){
        Intent intent=new Intent(context,SubmitOrderActivity.class);
        intent.putExtra("prePayInfoBean",prePayInfoBean);
        intent.putExtra("marklocation",markLocationBean);

        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_order;
    }

    @Override
    protected void initToolbar() {
        tvTitle.setText("提交订单");

    }

    @Override
    protected void initEventAndData() {
        mPrePayInfoBean=(PrePayInfoBean) getIntent().getSerializableExtra("prePayInfoBean");
        mMarkLocationBean=(MarkLocationBean) getIntent().getSerializableExtra("marklocation") ;
        handlerData();
        initRecyclerView();
        hourSdf=new SimpleDateFormat("HH:mm",Locale.CHINA);
        takeFoodTime=hourSdf.format(new Date());
        initView();
    }

   void initView(){
       tvName.setText(mPrePayInfoBean.getChargeInfo().getName());
       tvLocation.setText(mPrePayInfoBean.getChargeInfo().getAddress());
       tvPhone.setText(mPresenter.getPhone());
       tvActualCount.setText("实付: ￥"+actualPrice);
       tvTotal.setText("共"+quantity+"件商品,小计: ￥"+totalPrice);
       tvDiscount.setText("优惠: ￥"+(totalPrice-actualPrice));
       tvAll.setText("￥ "+totalPrice);
       tvTime.setText(takeFoodTime);
   }

    void initRecyclerView(){
        mSubmitFoodAdapter =new SimpleFoodAdapter(new ArrayList<>(mPrePayInfoBean.getFoodList()),this);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        rvFoodList.setAdapter(mSubmitFoodAdapter);

    }

    void handlerData(){

     couponSelect=new ArrayList<>();
     for (PrePayInfoBean.FoodListBean foodListBean:mPrePayInfoBean.getFoodList()){
         quantity+=foodListBean.getQuantity();
         totalPrice+=Float.parseFloat(foodListBean.getPrice())*foodListBean.getQuantity();
         actualPrice+=foodListBean.getFinalSum();
     }
     for (CouponsBean couponsBean:mPrePayInfoBean.getAvailableCoupons()){

     }



    }


    @Override
    public void toPay(WechatPayBean wechatPayBean) {
        WechatPayBean.SignedPayInfoBean signedPayInfoBean=wechatPayBean.getSignedPayInfo();
        api= WXAPIFactory.createWXAPI(this,signedPayInfoBean.getAppid(),true);
        api.registerApp(signedPayInfoBean.getAppid());
        PayReq req=new PayReq();
        req.appId=signedPayInfoBean.getAppid();
        req.partnerId=signedPayInfoBean.getPartnerid();
        req.prepayId=signedPayInfoBean.getPrepayid();
        req.packageValue="Sign=WXPay";
        req.nonceStr=signedPayInfoBean.getNoncestr();
        req.timeStamp=System.currentTimeMillis()+"";
        req.sign=signedPayInfoBean.getSign();
        api.sendReq(req);

    }
}
