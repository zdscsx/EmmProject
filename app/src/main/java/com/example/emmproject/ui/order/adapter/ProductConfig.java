package com.example.emmproject.ui.order.adapter;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.app.Constants;
import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.bean.order.ElemeGroupedItem;
import com.example.emmproject.R;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.example.emmproject.ui.order.OrderDialog;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.kunminx.linkage.contract.ILinkageSecondaryAdapterConfig;

import java.util.ArrayList;

/**
 * Create by 早点睡 at 20/1/12
 */
public class ProductConfig implements ILinkageSecondaryAdapterConfig {

    private Context mContext;
    OrderFragmentContract.ItemChangeCallback<ShopCardFoodBean> callback;
    private ArrayList<ShopCardFoodBean> mShopCardList;  //购物车adapter里的arraylit



    @Override
    public void setContext(Context context) {
        mContext=context;
    }

    public void setCallback(OrderFragmentContract.ItemChangeCallback<ShopCardFoodBean> callback){
        this.callback=callback;

    }

    public void setmShopCardList(ArrayList<ShopCardFoodBean> mShopCardList) {
        this.mShopCardList = mShopCardList;
    }

    @Override
    public void onBindViewHolder(LinkageSecondaryViewHolder holder, BaseGroupedItem item) {
        ElemeGroupedItem.ItemInfo foodItem=(ElemeGroupedItem.ItemInfo) item.info;
        StoreFoodBean.FoodListBean food=foodItem.getFoodListBean();

        if (!item.isHeader){
            if (foodItem.isEmpty()){  //是一个空视图
                holder.getView(R.id.rl_order_product).setVisibility(View.GONE);
                holder.getView(R.id.tv_second_tip).setVisibility(View.VISIBLE);
            }
            else {
                ImageView imageView = (ImageView) holder.getView(R.id.iv_second_product);
                TextView contentTv = (TextView) holder.getView(R.id.tv_second_content);
                TextView nameTv = (TextView) holder.getView(R.id.tv_second_title);
                TextView priceTv = (TextView) holder.getView(R.id.tv_second_cost);
                priceTv.setText(food.getPrice());
                RoundedCorners roundedCorners = new RoundedCorners(Constants.ROUND_CORNERS);
                RequestOptions coverRequestOptions = new RequestOptions()
                        .transforms(new CenterCrop(), roundedCorners);
                Glide.with(mContext).load(food.getMainImage()).apply(coverRequestOptions).into(imageView);
                contentTv.setText(food.getIntroduction());
                nameTv.setText(food.getName());
                ImageButton addButton = (ImageButton) holder.getView(R.id.bt_order_add);
                ImageButton reduceButton = (ImageButton) holder.getView(R.id.bt_order_reduce);
                TextView quantityTv = (TextView) holder.getView(R.id.tv_order_quantity);

                holder.itemView.setOnClickListener(o ->
                        new OrderDialog(mContext, (ElemeGroupedItem) item, mShopCardList, callback).showOrderDialog());
                if (!foodItem.hasFoodOption()) {

                }
    /*        if (((ElemeGroupedItem.ItemInfo) item.info).getQuantity()>0)
            { reduceButton  .setVisibility(View.VISIBLE);
                quantityTv.setVisibility(View.VISIBLE);
            }
            else   {reduceButton.setVisibility(View.GONE);
                quantityTv.setVisibility(View.GONE);}*/
       /*   addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ElemeGroupedItem.ItemInfo) item.info).setQuantity(true);
                    if (((ElemeGroupedItem.ItemInfo) item.info).getQuantity()>0)
                    {   reduceButton.setVisibility(View.VISIBLE);
                        quantityTv.setVisibility(View.VISIBLE);
                    }

                    quantityTv.setText((elemeGroupedItem.info).getQuantity()+"");
                    callback.onAddItem(elemeGroupedItem);
                }
            });
          /*  reduceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ElemeGroupedItem.ItemInfo) item.info).setQuantity(false);
                    if (((ElemeGroupedItem.ItemInfo) item.info).getQuantity()<=0)
                    {   reduceButton.setVisibility(View.GONE);
                        quantityTv.setVisibility(View.GONE);
                    }

                    quantityTv.setText((elemeGroupedItem .info).getQuantity()+"");
                    callback.onReduceItem(elemeGroupedItem);
                }
            });*/
            }

        }



    }

    @Override
    public void onBindHeaderViewHolder(LinkageSecondaryHeaderViewHolder holder, BaseGroupedItem item) {
        TextView header=(TextView)  holder.itemView.findViewById(R.id.tv_order_header);
        header.setText(item.header);
        header.setTextColor(mContext.getResources().getColor(R.color.black ));

    }

    @Override
    public void onBindFooterViewHolder(LinkageSecondaryFooterViewHolder holder, BaseGroupedItem item) {
    }

    @Override
    public int getGridLayoutId() {
        return 0;
    }

    @Override
    public int getLinearLayoutId() {
        return R.layout.item_product;
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.item_header;
    }

    @Override
    public int getFooterLayoutId() {
        return R.layout.item_footer;
    }

    @Override
    public int getHeaderTextViewId() {
        return R.id.tv_order_header;
    }

    @Override
    public int getSpanCountOfGridMode() {
        return 0;
    }
}

