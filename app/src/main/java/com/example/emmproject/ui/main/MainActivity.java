package com.example.emmproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.main.MainContract;
import com.example.emmproject.core.bean.main.TabContentEntity;
import com.example.emmproject.presenter.main.MainPresenter;
import com.example.emmproject.ui.history.fragment.HistoryFragment;
import com.example.emmproject.ui.mine.MineFragment;
import com.example.emmproject.ui.order.OrderFragment;
import com.example.emmproject.utils.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter > implements MainContract.View {

    /*    @BindView(R.id.vp_main_viewpage)
        ViewPager viewPager;

        @BindView(R.id.alphaIndicator)
        AlphaTabsIndicator alphaTabsIndicator;
    */
    @BindView(R.id.tab_main_tab)
    CommonTabLayout commonTabLayout;
    private ArrayList<CustomTabEntity> customTabEntities=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    static void startActicity(Context context){

        context.startActivity(new Intent(context,MainActivity.class));

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {
        initViewpage();

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
    }

    void initViewpage(){
       mPresenter.refreshToken();
        fragments.add(OrderFragment.newInstance());
        fragments.add(HistoryFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        customTabEntities.add(new TabContentEntity("点餐",R.mipmap.imageview_order_select,R.mipmap.imageview_order));
        customTabEntities.add(new TabContentEntity("历史订单",R.mipmap.imageview_order_select,R.mipmap.imageview_orderlist));
        customTabEntities.add(new TabContentEntity("我的",R.mipmap.imageview_me_select,R.mipmap.imageview_me));
        commonTabLayout.setTabData(customTabEntities,this,R.id.fl_main_container,fragments);
      //  LoginActivity.startActivity(this);


    }
}

