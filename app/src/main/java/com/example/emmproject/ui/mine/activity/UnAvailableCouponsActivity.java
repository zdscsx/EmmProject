package com.example.emmproject.ui.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.UnAvailableCouponsContract;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.presenter.mine.UnavailableCouponsPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class UnAvailableCouponsActivity extends BaseActivity<UnavailableCouponsPresenter> implements UnAvailableCouponsContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @OnClick(R.id.ibt_toolbar_back)
    void onClick(View  view){
        switch (view.getId()){
            case R.id.ibt_toolbar_back:
                finish();
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_available_coupons);
    }

   public static void startActivity(Context context){
       Intent intent=new Intent(context,UnAvailableCouponsActivity.class);
       context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_un_available_coupons;
    }

    @Override
    protected void initToolbar() {

        tvTitle.setText("无效优惠卷");
    }

    @Override
    protected void initEventAndData() {
       mPresenter.getUnavailableCoupons();
    }



    @Override
    public void showUnAvailableCoupons(ArrayList<CouponsBean> couponsBeans) {

    }

    @Override
    public void showEmptyAvailableCoupons() {

    }
}
