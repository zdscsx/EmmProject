package com.example.emmproject.ui.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.CouponsContract;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.presenter.mine.CouponsPresenter;
import com.example.emmproject.presenter.mine.UnavailableCouponsPresenter;
import com.example.emmproject.ui.mine.adapter.CouponsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class CouponsActivity extends BaseActivity<CouponsPresenter> implements CouponsContract.View {

    @BindView(R.id.ly_empty)
    LinearLayout lyEmpty;
    @BindView(R.id.tv_empty_tip)
    TextView tvEmptyTip;
    @BindView(R.id.rv_coupons_list)
    RecyclerView rvCouponsList;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_toolbar_more)
    TextView tvMore;

   private ArrayList<CouponsBean> mCouponsBeans;
   private CouponsAdapter mCouponsAdapter;

   @OnClick({R.id.tv_toolbar_more,R.id.ibt_toolbar_back})
   void onClick(View view){
       switch (view.getId()){
           case R.id.tv_toolbar_more:
               UnAvailableCouponsActivity.startActivity(this);
           case R.id.ibt_toolbar_back:
               finish();
       }

   }

   public static void startActivity(Context context){
       Intent intent=new Intent(context,CouponsActivity.class);
       context.startActivity(intent);

   }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coupons;

   }

    @Override
    protected void initToolbar() {

       tvMore.setText("查看无效卷");
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showCouponsFail() {

    }

    @Override
    public void showCoupons(ArrayList<CouponsBean> couponsBeans) {

    }

    @Override
    public void showCouponsEmpty() {
        lyEmpty.setVisibility(View.VISIBLE);
        tvEmptyTip.setText("暂无可用优惠卷");
   }
}
