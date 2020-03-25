package com.example.emmproject.ui.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.ActivityContract;
import com.example.emmproject.presenter.mine.ActivityPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityActivity extends BaseActivity<ActivityPresenter> implements ActivityContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    public static void startActivity(Context context){
        Intent intent=new Intent( context,ActivityActivity.class);
        context.startActivity(intent);

    }

    @OnClick(R.id.ibt_toolbar_back)
    void  onClick(View view ){
        switch (view.getId()){
            case R.id.ibt_toolbar_back:
                finish();
                break;
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_activity;
    }

    @Override
    protected void initToolbar() {
        tvTitle.setText("充电站活动");

    }

    @Override
    protected void initEventAndData() {

    }
}
