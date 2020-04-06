package com.example.emmproject.ui.history.activity;

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
import com.example.emmproject.contract.history.OrderInfoContract;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.core.bean.history.OrderInfoBean;
import com.example.emmproject.core.bean.order.Food;
import com.example.emmproject.core.bean.order.FoodBean;
import com.example.emmproject.presenter.history.OrderInfoPresenter;
import com.example.emmproject.ui.order.adapter.SimpleFoodAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderInfoActivity extends BaseActivity<OrderInfoPresenter> implements OrderInfoContract{

    @BindView(R.id.rv_orderinfo_orderlist)
    RecyclerView rvFoodList;
    @BindView(R.id.tv_orderinfo_code)
    TextView tvTakeFoodCode;
    @BindView(R.id.tv_orderinfo_state)
    TextView tvState;
    @BindView(R.id.tv_orderinfo_cost)
    TextView tvCost;
    @BindView(R.id.tv_orderinfo_location)
    TextView tvLocation;
    @BindView(R.id.tv_orderinfo_name)
    TextView tvName;
    @BindView(R.id.tv_orderinfo_note)
    TextView tvNote;
    @BindView(R.id.tv_orderinfo_number)
    TextView tvNumber;
    @BindView(R.id.tv_orderinfo_pay)
    TextView tvPayWay;
    @BindView(R.id.tv_orderinfo_paytime)
    TextView tvPayTime;
    @BindView(R.id.tv_orderinfo_phone)
    TextView tvPhone;
    @BindView(R.id.tv_orderinfo_pretime)
    TextView tvPreTime;
    @BindView(R.id.tv_orderinfo_quantity)
    TextView tvQuantity;
    @BindView(R.id.tv_orderinfo_taketime)
    TextView tvTakeTime;
    @BindView(R.id.tv_orderinfo_time)
    TextView tvSubmitTime;
    @BindView(R.id.tv_toolbar_title)
    TextView tvtitle;


    private SimpleFoodAdapter mSimpleFoodAdapter;
    private OrderHistoryBean mOrderHistoryBean;
    private OrderInfoBean mOrderInfoBean;
    private OrderHistoryBean.ChargeInfoBean mChargeInfoBean;
    private FoodBean[] mFoodBeans;

    @OnClick(R.id.ibt_toolbar_back)
    void onClick(android.view.View view){
        switch (view.getId()){
            case R.id.ibt_toolbar_back:finish();

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info;
    }

    public static void startActivity(Context context, OrderHistoryBean orderHistoryBean){
        Intent intent=new Intent(context,OrderInfoActivity.class);
        intent.putExtra("orderinfo",orderHistoryBean);
        context.startActivity(intent);
    }
    @Override
    protected void initToolbar() {

        tvtitle.setText("订单详情");
    }

    @Override
    protected void initEventAndData() {
        mOrderHistoryBean = (OrderHistoryBean) getIntent().getSerializableExtra("orderinfo");
        mOrderInfoBean = mOrderHistoryBean.getOrderInfo();
        mChargeInfoBean = mOrderHistoryBean.getChargeInfo();
        mFoodBeans = mOrderHistoryBean.getFoodList();
        initView();
        mSimpleFoodAdapter=new SimpleFoodAdapter(new ArrayList<Food>(Arrays.asList(mFoodBeans)),this);
        rvFoodList.setAdapter(mSimpleFoodAdapter);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
    }

    void initView(){
        String status;
        switch (mOrderInfoBean.getBusinessStatus()){
            case Constants.ORDER_STATUS_FINISH: status="已完成";
                break;
            case Constants.ORDER_STATUS_CANCEL :status="已取消";
                break;
            case Constants.ORDER_STATUS_WAITPAY: status="等待支付";

                break;
            case Constants.ORDER_STATUS_WAITAKE:status="等待取餐";
                tvTakeFoodCode.setVisibility(android.view.View.VISIBLE);
                tvTakeFoodCode.setText(mOrderInfoBean.getTakeFoodCode());
                break;
            default:status="已完成";
        }
        int quantity=0;
        float cost=0;
        for (FoodBean foodBean:mFoodBeans){
            quantity+=foodBean.getQuantity();
            cost+=foodBean.getFinalSum();
        }
        tvState.setText(status);
        tvName.setText(mChargeInfoBean.getName());
        tvLocation.setText(mChargeInfoBean.getAddress());
        tvNumber.setText("订单编号: "+ mOrderInfoBean.getTrade_no());
        tvPayWay.setText("支付方式: 微信支付");
        tvSubmitTime.setText("下单时间: "+ mOrderInfoBean.getCreateTime());
        tvPreTime.setText("预约时间: "+ mOrderInfoBean.getTakeFoodTime());
        tvPayTime.setText("支付时间: "+ mOrderInfoBean.getPayTime());
        tvNote.setText("备注: "+ mOrderInfoBean.getNote());
        tvQuantity.setText("共"+quantity+"件");
        tvCost.setText(" ￥"+Math.round(cost*100)/100.0);
    }
}
