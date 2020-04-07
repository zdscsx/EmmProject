package com.example.emmproject.ui.order.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseRootActivity;
import com.example.emmproject.ui.order.adapter.MarkListAdapter;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.order.MapContract;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.presenter.order.MapPresenter;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;


import butterknife.BindView;
import butterknife.OnClick;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MapActivity extends BaseRootActivity<MapPresenter> implements  MapContract.View {


    @BindView(R.id.rv_map_marklist)
    RecyclerView rvMrakList;
    @BindView(R.id.tv_toolbar_title)
    TextView tvName;
    @BindView(R.id.map)
    MapView mapView;

    private TencentMap mTencentMap;
    private ArrayList<MarkLocationBean > mMarkLocationBeans;
    private MarkListAdapter mMarkListAdapter;
    private LatLng mCurrentLatlng;
    @OnClick(R.id.ibt_toolbar_back)
    void  onClick(View view){
        switch (view.getId()){
            case R.id.ibt_toolbar_back: finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作。
    }


   public static void startAvtivity(Context context){
        context.startActivity(new Intent(context,MapActivity.class));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void initToolbar() {
        tvName.setText("地图");
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        requestPermissions();

        mMarkLocationBeans=new ArrayList<>();
        mMarkListAdapter=new MarkListAdapter(this,mMarkLocationBeans);
        rvMrakList.setLayoutManager(new LinearLayoutManager(this));
        rvMrakList.setAdapter(mMarkListAdapter);
        mMarkListAdapter.setmProjection(mapView.getProjection());
        mPresenter.getMarkLocation();
        mTencentMap=mapView.getMap();
        mTencentMap.setZoom(15);
        getLocation();

       /* TencentMap tencentMap=mapView.getMap();
        tencentMap.setMyLocationEnabled(true);
    /*    mapView.getMap().setPointToCenter(0,0);
        tencentMap.setCameraCenterProportion(0,0,true);
        tencentMap.setMapCenterAndScale(1,1,1);*/
/*    tencentMap.setCameraCenterProportion(550,505);
        Marker marker = tencentMap.addMarker(new MarkerOptions()
                .position(new LatLng(22,11))
                .title("55")
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(true));
        marker.showInfoWindow();// 设置默认显示一个infoWindow*/

    }

    @Override
    public int getNornalId() {
        return R.id.ly_map_error;
    }

    @Override
    public void reload() {
        mPresenter.getMarkLocation();
    }

    public void getLocation(){

        TencentLocationListener tencentLocationListener=new TencentLocationListener() {  //定位监听 获取当前位置
            @Override
            public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
                mCurrentLatlng=new LatLng(tencentLocation.getLatitude(),tencentLocation.getLongitude());
                mMarkListAdapter.setmCurrentLatLng(mCurrentLatlng); //传入当前坐标后，刷新adapter 获取距离
                mMarkListAdapter.notifyDataSetChanged();
                mTencentMap.setCenter(mCurrentLatlng);
                TencentLocationManager.getInstance(MapActivity.this).removeUpdates(this);
                  }

            @Override
            public void onStatusUpdate(String s, int i, String s1) { }
        };
        TencentLocationRequest locationRequest=TencentLocationRequest.create();
        int error=TencentLocationManager.getInstance(this).requestLocationUpdates(locationRequest, tencentLocationListener);//监听状态码
    }


    @Override
    public void setMarketList(MarkLocationBean[] locationBeans) {
        showNormal();
        mMarkLocationBeans.clear();
        Collections.addAll(mMarkLocationBeans,locationBeans);
        mMarkListAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }


}
