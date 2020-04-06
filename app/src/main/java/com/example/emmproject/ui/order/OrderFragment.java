package com.example.emmproject.ui.order;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.emmproject.R;
import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.core.bean.order.PrePayInfoBean;
import com.example.emmproject.ui.order.activity.SubmitOrderActivity;
import com.example.emmproject.ui.order.adapter.ProductConfig;
import com.example.emmproject.ui.order.adapter.ShopConfig;
import com.example.emmproject.base.fragment.BaseFragment;
import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.bean.order.ElemeGroupedItem;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.presenter.order.OrderFragmentPresenter;
import com.example.emmproject.ui.order.activity.MapActivity;
import com.example.emmproject.ui.order.activity.SearchActivity;
import com.example.emmproject.ui.order.adapter.ShoppingCardAdapter;

import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.widget.LimitSizeLinearLayout;
import com.kunminx.linkage.LinkageRecyclerView;
import com.kunminx.linkage.bean.BaseGroupedItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class OrderFragment extends BaseFragment<OrderFragmentPresenter> implements OrderFragmentContract.View , OrderFragmentContract.ItemChangeCallback<ShopCardFoodBean> {


    @BindView(R.id.lr_order_list)
    LinkageRecyclerView<ElemeGroupedItem.ItemInfo> recyclerView;
    @BindView(R.id.rv_order_shoppcardlist)
    RecyclerView shoppingCardRecyclerView;
    @BindView(R.id.tv_order_number)
    TextView numberTv;
    @BindView(R.id.bt_order_pay)
    Button payBt;
    @BindView(R.id.ly_order_shopcard)
    RelativeLayout lyshopCard;
    @BindView(R.id.ly_order_shopcardlist)
    LimitSizeLinearLayout lYshopcardLsit;
    @BindView(R.id.fl_order)
    FrameLayout mFrameLayout;
    @BindView(R.id.tv_order_location)
    TextView tvLocation;
    @BindView(R.id.tv_order_distance)
    TextView tvDistance;

    private MarkLocationBean mLocationBean;
    private boolean isShopcardShow=false;
    private Integer quantity=0;
    private ShopConfig mShopConfig;
    private ProductConfig mProductConfig;
    private ArrayList<ShopCardFoodBean> shoppingCardList;
    private ShoppingCardAdapter shoppingCardAdapter;
    private ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>> productList;
    private View  mPopupview;
    private PopupWindow mPopupWindow;


    private static volatile OrderFragment orderFragment;

    public static OrderFragment getInstance() {
        if (orderFragment==null){
            synchronized (OrderFragment.class){
                if (orderFragment==null)
                    orderFragment=new OrderFragment();
            }
        }
        return orderFragment;
    }

    @Override
    public void inject() {
        EmmApplication.getComponent().inject(this);
    }



    @Override
    protected void initView() {

    }


    private void initRecyclerView(){
        mShopConfig=new ShopConfig();
        mShopConfig.setContext(getContext());
        mProductConfig=new ProductConfig();
        mProductConfig.setCallback(this);
        shoppingCardList=new ArrayList<>();
        shoppingCardAdapter= new ShoppingCardAdapter(shoppingCardList,getContext());
        shoppingCardAdapter.setCallback(this);
        mProductConfig.setmShopCardList(shoppingCardList);
        shoppingCardRecyclerView.setAdapter(shoppingCardAdapter);
        shoppingCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @OnClick({R.id.rl_order_location,R.id.bt_order_pay,R.id.view_order_showshoppcard,R.id.tv_order_clear})
    void onCLick(View view){
        switch (view.getId()){
            case R.id.rl_order_location : MapActivity.startAvtivity(getContext());
                break;
//            case R.id.rl_order_search :
//                SearchActivity.startActivity(getContext());
//                break;
            case R.id.bt_order_pay:
                 mPresenter.submitOrder(mLocationBean.getChargeId(),shoppingCardList);
                break;
            case R.id.view_order_showshoppcard:
                changeShopCardState();
                 break;
            case R.id.tv_order_clear:
                shoppingCardList.clear();
                quantity=0;
                changeShopCardQuantity();
                if (isShopcardShow)
                changeShopCardState();

        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initEventAndData() {
        initRecyclerView();
        mPopupview=LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_transparent,null,false);
        mPopupview.setOnClickListener(o-> changeShopCardState());//点击view即购物车外面购物车消失
        mPopupWindow=new PopupWindow(mPopupview, Toolbar.LayoutParams.MATCH_PARENT,
                mFrameLayout.getHeight()-lyshopCard.getHeight());


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setMarkLocation(MarkLocationBean markLocation){
        mLocationBean=markLocation;
        tvLocation.setText(mLocationBean.getName());
        if (markLocation.getDistance()!=0)
        tvDistance.setText("距离你"+markLocation.getDistance()+"km");
        mPresenter.getProduct(mLocationBean.getChargeId());


    }

    @Override
    public void showProduct(ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>> itemInfos) {
        productList=itemInfos;
        recyclerView.init(productList,mShopConfig,mProductConfig);

    }

    @Override
    public void toSubmitActivity(PrePayInfoBean prePayInfoBean) {
        SubmitOrderActivity.startActivity(getActivity(),prePayInfoBean,mLocationBean);
    }


    @Override
    public void onAddItem(ShopCardFoodBean elemeGroupedItem) {

        elemeGroupedItem.changeQuantity(true);
       if (!shoppingCardList.contains(elemeGroupedItem))
            shoppingCardList.add(elemeGroupedItem);
       if(isShopcardShow){

           shoppingCardRecyclerView.scrollToPosition(shoppingCardList.size()-1);
       }
        quantity++;
        changeShopCardQuantity();
    }

    @Override
    public void onReduceItem(ShopCardFoodBean elemeGroupedItem) {
        elemeGroupedItem.changeQuantity(false);
        if (elemeGroupedItem.getQuantity()==0) {  //两种结果 如果购物车为空阴影直接消失，否则高度变化
            shoppingCardList.remove(elemeGroupedItem);
            shoppingCardAdapter.notifyDataSetChanged();//notify后height变化，阴影高度改变
            if (shoppingCardList.size()==0) {
                if(isShopcardShow){
                    changeShopCardState();
                }
            }
            else {
            if (isShopcardShow){
                mPopupWindow.dismiss();
                showPopupWindow();
            }}
        }
        quantity--;
        changeShopCardQuantity();
    }

    void changeShopCardQuantity(){
        if (shoppingCardList.size()!=0){
            payBt.setVisibility(View.VISIBLE);
        }
        else {
            payBt.setVisibility(View.GONE);
        }
        shoppingCardAdapter.notifyDataSetChanged();
        recyclerView.getSecondaryAdapter().notifyDataSetChanged();
        numberTv.setText(quantity.toString());
    }




    public void changeShopCardState(){
        if (isShopcardShow) {
            lYshopcardLsit.setVisibility(View.GONE);
            mPopupWindow.dismiss();
        }
        else {
            lYshopcardLsit.setVisibility(View.VISIBLE);
            showPopupWindow();
        }

        isShopcardShow=!isShopcardShow;
    }


    public void showPopupWindow(){
        lyshopCard.post(new Runnable() {
            @Override
            public void run() {  //这个是购物车外的阴影效果 要post才可以获取到Measure后的高
                mPopupWindow.setHeight(mFrameLayout.getHeight()-lyshopCard.getHeight());
                mPopupWindow.showAtLocation(mFrameLayout,Gravity.TOP,0,0);
            }
        });

    }
    public boolean isShopcardShow() {
        return isShopcardShow;
    }

    public void setShopcardShow(boolean shopcardShow) {
        isShopcardShow = shopcardShow;
    }
}
