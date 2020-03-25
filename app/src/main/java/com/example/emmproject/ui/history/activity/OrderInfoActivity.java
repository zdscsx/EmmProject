package com.example.emmproject.ui.history.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.history.OrderInfoContract;
import com.example.emmproject.presenter.history.OrderInfoPresenter;

public class OrderInfoActivity extends BaseActivity<OrderInfoPresenter> implements OrderInfoContract{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info;
    }

    public static void startActivity(Context context){
      context.startActivity(new Intent(context,OrderInfoActivity.class));
    }
    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
