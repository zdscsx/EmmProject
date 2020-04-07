package com.example.emmproject.ui.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.UnAvailableCouponsContract;
import com.example.emmproject.core.bean.mine.CouponsBean;
import com.example.emmproject.presenter.mine.UnavailableCouponsPresenter;
import com.example.emmproject.ui.mine.adapter.CouponsAdapter;
import com.example.emmproject.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class UnAvailableCouponsActivity extends BaseActivity<UnavailableCouponsPresenter> implements UnAvailableCouponsContract.View {


    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_empty_tip)
    TextView tvEmpty;
    @BindView(R.id.ly_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.rv_uncoupons_list)
    RecyclerView rvCouponsList;


    private ArrayList<CouponsBean> mCouponsBeans=new ArrayList<>();
    private CouponsAdapter mCouponsAdapter;
    @OnClick(R.id.ibt_toolbar_back)
    void onClick(View  view){
        switch (view.getId()){
            case R.id.ibt_toolbar_back:
                finish();
                break;
        }

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
        mCouponsAdapter=new CouponsAdapter(mCouponsBeans);
        rvCouponsList.setAdapter(mCouponsAdapter);
        rvCouponsList.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.getUnavailableCoupons();
    }



    @Override
    public void showUnAvailableCoupons(ArrayList<CouponsBean> couponsBeans) {
        LogUtils.logd(couponsBeans.size()+"size");
        rlEmpty.setVisibility(View.GONE);
        mCouponsBeans.clear();
        mCouponsBeans.addAll(couponsBeans);
        mCouponsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyAvailableCoupons() {
        LogUtils.logd("empty");
        rlEmpty.setVisibility(View.VISIBLE);
        tvEmpty.setText("没有查找到无效优惠卷");
    }
}
