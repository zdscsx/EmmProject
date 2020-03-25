package com.example.emmproject.ui.order.activity;

import android.content.Context;
import android.content.Intent;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.order.SearchContract;
import com.example.emmproject.presenter.order.SearchPresenter;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {



   static public void startActivity(Context context){
        context.startActivity(new Intent(context,SearchActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
