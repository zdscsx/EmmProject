package com.example.emmproject.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.fragment.app.Fragment;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.main.MainContract;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.main.TabContentEntity;
import com.example.emmproject.presenter.main.MainPresenter;
import com.example.emmproject.ui.history.fragment.HistoryFragment;
import com.example.emmproject.ui.mine.MineFragment;
import com.example.emmproject.ui.order.OrderFragment;
import com.example.emmproject.utils.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.LatLng;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter > implements MainContract.View {


    @BindView(R.id.tab_main_tab)
    CommonTabLayout commonTabLayout;

    private OrderFragment mOrderFragment;

    private ArrayList<CustomTabEntity> customTabEntities=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private LatLng mLatLng;
    private MarkLocationBean mLocationBean;

    public static void startActicity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }

   public static void startActivity(Context context,MarkLocationBean markLocationBean){ //这个
        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra("location",markLocationBean);
        LogUtils.<MarkLocationBean>logGson(markLocationBean);
       context.startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {//这个是选择充电桩后的回调 ，更换充电桩
            super.onNewIntent(intent);
            MarkLocationBean markLocationBean=(MarkLocationBean) intent.getSerializableExtra("location");
            if (markLocationBean!=null) {
                mOrderFragment.setMarkLocation(markLocationBean);
            }



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
        mPresenter.refreshToken();
        initViewpage();
        requestPermissions();
    }


    private void requestPermissions(){ //申请定位权限
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions, 0);
            }else{
              getLocation();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    getLocation();
                }

        }
        //可在此继续其他操作。
    }


    public void getLocation(){

        TencentLocationListener tencentLocationListener=new TencentLocationListener() {  //定位监听 获取当前位置
            @Override
            public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
                 mLatLng=new LatLng(tencentLocation.getLatitude(),tencentLocation.getLongitude());
                 LogUtils.logd(mLatLng.toString()+" "+mLatLng.getLatitude());
                 TencentLocationManager.getInstance(MainActivity.this).removeUpdates(this);
                 mPresenter.getMarkLocation(mLatLng);
            }

            @Override
            public void onStatusUpdate(String s, int i, String s1) { }
        };
        TencentLocationRequest locationRequest=TencentLocationRequest.create();
        int error=TencentLocationManager.getInstance(this).requestLocationUpdates(locationRequest, tencentLocationListener);//监听状态码
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
    }

    void initViewpage(){
        mOrderFragment=OrderFragment.getInstance();
        fragments.add(mOrderFragment);
        fragments.add(HistoryFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        customTabEntities.add(new TabContentEntity("点餐",R.mipmap.imageview_order_select,R.mipmap.imageview_order));
        customTabEntities.add(new TabContentEntity("历史订单",R.mipmap.imageview_order_select,R.mipmap.imageview_orderlist));
        customTabEntities.add(new TabContentEntity("我的",R.mipmap.imageview_me_select,R.mipmap.imageview_me));
        commonTabLayout.setTabData(customTabEntities,this,R.id.fl_main_container,fragments);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener(){
            @Override
            public void onTabSelect(int position) {
                //这个做了个特殊处理，就是在orderFragment中点击显示了购物车时，再点击下面tab切换时，隐藏购物车阴影
                if (mOrderFragment.isShopcardShow())
                    mOrderFragment.changeShopCardState();

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void showLocation(MarkLocationBean locationBean) {
        mOrderFragment.setMarkLocation(locationBean);

    }
}

