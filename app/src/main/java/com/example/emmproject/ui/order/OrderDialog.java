package com.example.emmproject.ui.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.example.emmproject.utils.LogUtils;
import com.kongzue.dialog.v3.CustomDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDialog {
    //再次封装了一下dailog框架
    //是点击菜品详情dialog

    //bindview
    @BindView(R.id.iv_orderdialog_pic)
    ImageView imageView;
    @BindView(R.id.bt_orderdialog_add)
    ImageButton btAdd ;
    @BindView(R.id.bt_orderdialog_reduce)
    ImageButton btReduce;
    @BindView(R.id.tv_order_quantity)
    TextView tvQuantity ;
    @BindView(R.id.tv_orderdialog_introduce)
    TextView tvIntroduction ;
    @BindView(R.id.tv_orderdialog_name)
    TextView tvName  ;
    @BindView(R.id.bt_orderdialog_big)
    Button btBig ;//大份按钮
    @BindView(R.id.bt_orderdialog_small)
    Button btSmall;
    @BindView(R.id.tv_orderdialog_cost)
    TextView tvCost ; //总价
    @BindView(R.id.tv_orderdialog_describe)
    TextView tvDescribe;//大份还是小份
    @BindView(R.id.tv_orderdialog_option)
    TextView tvOption;
    @BindView(R.id.ly_orderdialog_selection)
    LinearLayout lySelection;
    @BindView(R.id.rv_orderdialog_change)
    RelativeLayout rlchange;
    @BindView(R.id.bt_orderdialog_addshop)
    Button btAddShop;

    private boolean mIsCanSelect;//可以选择大小份
    private int mQuantity;//份数
    private double mPrice;//价格
    private Context mContext;
    private ElemeGroupedItem food;
    private StoreFoodBean.FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean smallSelection;
    private StoreFoodBean.FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean bigSelection;
    private ShopCardFoodBean shopCardFoodBean;
    private ArrayList<ShopCardFoodBean> mShopCardFoodArrayList;


    public OrderDialog(Context mContext,ElemeGroupedItem elemeGroupedItem,ArrayList<ShopCardFoodBean> mShopCardFoodArrayList) {
        this.mContext = mContext;
         this.food=elemeGroupedItem;
         this.mShopCardFoodArrayList=mShopCardFoodArrayList;
    }


    @OnClick({R.id.bt_orderdialog_addshop,R.id.bt_orderdialog_small,R.id.bt_orderdialog_big,R.id.bt_orderdialog_reduce,R.id.bt_orderdialog_add})
    void onClick(View view){
        switch (view.getId()){
            case R.id.bt_orderdialog_add:
                mQuantity=shopCardFoodBean.changeQuantity(true);
                showQuantityAndPrice();
                break;
            case R.id.bt_orderdialog_reduce:
                mQuantity= shopCardFoodBean.changeQuantity(false);
                showQuantityAndPrice();
                if (shopCardFoodBean.getQuantity()==0)//订单份数变为零.增减按钮消失
                {   btAddShop.setVisibility(View.VISIBLE);
                    rlchange.setVisibility(View.GONE);
                    mShopCardFoodArrayList.remove(shopCardFoodBean);
                };
                break;
            case R.id.bt_orderdialog_small:
                 shopCardFoodBean=new ShopCardFoodBean(food.getStoreInfoBean(),food.info.getFoodListBean());;
                shopCardFoodBean.setSelectionsBean(smallSelection);
                if (mShopCardFoodArrayList.contains(shopCardFoodBean)) //判断购物车是否已经添加该物，有就取出来
                    shopCardFoodBean=mShopCardFoodArrayList.get(mShopCardFoodArrayList.indexOf(shopCardFoodBean));
                if (shopCardFoodBean.getQuantity()!=0){
                    btAddShop.setVisibility(View.GONE);
                    rlchange.setVisibility(View.VISIBLE);
                }
                btSmall.setSelected(true);
                btBig.setSelected(false);
                shopCardFoodBean.getFoodListBean().setPrice(smallSelection.getPrice()+"");
                tvDescribe.setText(shopCardFoodBean.getSelectionsBeans().getSelectName());
                showQuantityAndPrice();
                break;
            case R.id.bt_orderdialog_big:
                shopCardFoodBean=new ShopCardFoodBean(food.getStoreInfoBean(),food.info.getFoodListBean());;
                shopCardFoodBean.setSelectionsBean(bigSelection);
                if (mShopCardFoodArrayList.contains(shopCardFoodBean)) //判断购物车是否已经添加该物，有就取出来
                    shopCardFoodBean=mShopCardFoodArrayList.get(mShopCardFoodArrayList.indexOf(shopCardFoodBean));
                if (shopCardFoodBean.getQuantity()==0){
                    btAddShop.setVisibility(View.VISIBLE);
                    rlchange.setVisibility(View.GONE);
                }
                btSmall.setSelected(false);
                btBig.setSelected(true);
                shopCardFoodBean.getFoodListBean().setPrice(bigSelection.getPrice()+"");
                tvDescribe.setText(shopCardFoodBean.getSelectionsBeans().getSelectName());
                showQuantityAndPrice();
                break;
            case R.id.bt_orderdialog_addshop:
                shopCardFoodBean.changeQuantity(true);
                showQuantityAndPrice();
                btAddShop.setVisibility(View.GONE);
                rlchange.setVisibility(View.VISIBLE);
                if (!mShopCardFoodArrayList.contains(shopCardFoodBean))
                    mShopCardFoodArrayList.add(shopCardFoodBean);
                break;


        }
    }





    public void showOrderDialog(){
        CustomDialog.show((AppCompatActivity) mContext, R.layout.dialog_order, new CustomDialog.OnBindView() {
            @Override
            public void onBind(CustomDialog dialog, View v) {
                ButterKnife.bind(OrderDialog.this,v);
             //   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT) .        ;

                 v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                 shopCardFoodBean=new ShopCardFoodBean(food.getStoreInfoBean(),food.info.getFoodListBean());;

                mIsCanSelect=food.info.getFoodListBean().getFoodOption()!=null;
                if (mIsCanSelect) {
                    shopCardFoodBean.setSelectionsBean(food.info.getFoodListBean().getFoodOption().getOptionList().get(0).getSelections().get(0));
                    if (mShopCardFoodArrayList.contains(shopCardFoodBean)) //判断购物车是否已经添加该物，有就取出来
                        shopCardFoodBean=mShopCardFoodArrayList.get(mShopCardFoodArrayList.indexOf(shopCardFoodBean));
                    btSmall.setSelected(true);//默认选择小份
                    smallSelection = food.info.getFoodListBean().getFoodOption().getOptionList().get(0).getSelections().get(0);
                    bigSelection = food.info.getFoodListBean().getFoodOption().getOptionList().get(0).getSelections().get(1);
                    tvDescribe.setText(shopCardFoodBean.getSelectionsBeans().getSelectName());
                }
                else { //没有选项就隐藏
                    if (mShopCardFoodArrayList.contains(shopCardFoodBean)) //判断购物车是否已经添加该物，有就取出来
                        shopCardFoodBean=mShopCardFoodArrayList.get(mShopCardFoodArrayList.indexOf(shopCardFoodBean));
                    tvOption.setVisibility(View.GONE);
                    tvDescribe.setVisibility(View.GONE);
                    lySelection.setVisibility(View.GONE); }
                if (shopCardFoodBean.getQuantity()!=0){
                    btAddShop.setVisibility(View.GONE);
                    rlchange.setVisibility(View.VISIBLE);
                }

                initView();
            }});
    }

    void initView(){
        Glide.with(mContext).load(shopCardFoodBean.getFoodListBean().getMainImage()) .apply(RequestOptions.centerCropTransform()).into(imageView);
        tvIntroduction.setText(shopCardFoodBean.getFoodListBean().getIntroduction());
        tvName.setText(shopCardFoodBean.getFoodListBean().getName());
        showQuantityAndPrice();
    }

    private void showQuantityAndPrice(){
        mQuantity=shopCardFoodBean.getQuantity();
        mPrice =Double.parseDouble(shopCardFoodBean.getFoodListBean().getPrice());
        LogUtils.logd(mQuantity+"");
        tvQuantity.setText(mQuantity+"");
        tvCost.setText(mQuantity>0?mQuantity*mPrice+"":mPrice+"");
    }


}
