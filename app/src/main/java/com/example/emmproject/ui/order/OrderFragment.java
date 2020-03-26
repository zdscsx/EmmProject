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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.emmproject.R;
import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.ui.order.adapter.ProductConfig;
import com.example.emmproject.ui.order.adapter.ShopConfig;
import com.example.emmproject.base.fragment.BaseFragment;
import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.bean.ElemeGroupedItem;
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
    LinearLayout lyshopCard;
    @BindView(R.id.ly_order_shopcardlist)
    LimitSizeLinearLayout lYshopcardLsit;
    @BindView(R.id.fl_order)
    FrameLayout mFrameLayout;
    private boolean isShopcardShow=false;
    private Integer quantity=0;
    private ShopConfig mShopConfig;
    private ProductConfig mProductConfig;
    private ArrayList<ShopCardFoodBean> shoppingCardList;
    private ShoppingCardAdapter shoppingCardAdapter;
    private ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>> productList;
    private View  mPopupview;
    private PopupWindow mPopupWindow;

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void inject() {
        EmmApplication.getComponent().inject(this);

    }



    @Override
    protected void initView() {

    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
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


    @OnClick({R.id.rl_order_location,R.id.rl_order_search,R.id.bt_order_pay,R.id.view_order_showshoppcard})
    void onCLick(View view){
        switch (view.getId()){
            case R.id.rl_order_location : MapActivity.startAvtivity(getContext());
                break;
            case R.id.rl_order_search :
                SearchActivity.startActivity(getContext());
                break;
            case R.id.bt_order_pay:
                Toast.makeText(getContext(),"sa000",Toast.LENGTH_SHORT).show();
                break;
            case R.id.view_order_showshoppcard:
                changeShopCardState();
                 break;

        }
    }

    private void changeShopCardState(){
        LogUtils.logd(shoppingCardList.size()+" size");
        if (isShopcardShow) {
            lYshopcardLsit.setVisibility(View.GONE);
            mPopupWindow.dismiss();
        }
        else {
            lYshopcardLsit.setVisibility(View.VISIBLE);
            lyshopCard.post(new Runnable() {
                @Override
                public void run() {  //这个是购物车外的阴影效果 要post才可以获取到Measure后的高
                    mPopupWindow=new PopupWindow(mPopupview, Toolbar.LayoutParams.MATCH_PARENT,
                            mFrameLayout.getHeight()-lyshopCard.getHeight());
                    mPopupWindow.showAtLocation(mFrameLayout,Gravity.TOP,0,0);
                }
            });

        }

        isShopcardShow=!isShopcardShow;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initEventAndData() {
        initRecyclerView();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getProduct(0);
        mPopupview=LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_transparent,null,false);
        mPopupview.setOnClickListener(o-> changeShopCardState());//点击view即购物车外面购物车消失


    }

    @Override
    public void showProduct(ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>> itemInfos) {
        productList=itemInfos;

        recyclerView.init(productList,mShopConfig,mProductConfig);

    }


    @Override
    public void onAddItem(ShopCardFoodBean elemeGroupedItem) {
        elemeGroupedItem.changeQuantity(true);
        payBt.setVisibility(View.VISIBLE);
       if (!shoppingCardList.contains(elemeGroupedItem))
            shoppingCardList.add(elemeGroupedItem);
        if (shoppingCardAdapter!=null)
            shoppingCardAdapter.notifyDataSetChanged();
        recyclerView.getSecondaryAdapter().notifyDataSetChanged();
        quantity++;
        numberTv.setText(quantity.toString());


    }

    @Override
    public void onReduceItem(ShopCardFoodBean elemeGroupedItem) {
        elemeGroupedItem.changeQuantity(false);
        if (elemeGroupedItem.getQuantity()==0)
            shoppingCardList.remove(elemeGroupedItem);
        if (shoppingCardList.size()==0)
        {
            payBt.setVisibility(View.GONE);
            if(isShopcardShow)
            changeShopCardState();//如果
        }
        if (shoppingCardAdapter!=null)
            shoppingCardAdapter.notifyDataSetChanged();
        recyclerView.getSecondaryAdapter().notifyDataSetChanged();
        quantity--;
        numberTv.setText(quantity.toString());
    }

}
