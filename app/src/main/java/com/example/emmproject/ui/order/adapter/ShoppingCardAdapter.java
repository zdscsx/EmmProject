package com.example.emmproject.ui.order.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.core.bean.order.StoreFoodBean.FoodListBean;
import com.example.emmproject.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ShoppingCardAdapter extends RecyclerView.Adapter<ShoppingCardAdapter.ViewHolder> {


    private   ArrayList<ShopCardFoodBean> shoplist;
    private Context mContext;
    private OrderFragmentContract.ItemChangeCallback<ShopCardFoodBean> callback;

    public void setCallback(OrderFragmentContract.ItemChangeCallback<ShopCardFoodBean> callback) {
        this.callback = callback;
    }

    public ShoppingCardAdapter(ArrayList<ShopCardFoodBean> shoplist, Context context) {
        this.shoplist = shoplist;
        this.mContext=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoppingcard,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShopCardFoodBean item=shoplist.get(position);
        holder.productIv.setImageResource(R.mipmap.imageview_test);
        FoodListBean food=item.getFoodListBean();
        RoundedCorners roundedCorners=new RoundedCorners(5);
        RequestOptions requestOptions=RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(mContext).load(food.getMainImage()).apply(requestOptions).into(holder.productIv);
        holder.titleTv.setText(food.getName());
        holder.contentTv.setText(food.getIntroduction());
        holder.costTv.setText(Double.parseDouble(item.getFoodListBean().getPrice())*item.getQuantity()+"");

      /*  if (( item.info).getQuantity()>0)
        {  holder.reduceBt .setVisibility(View.VISIBLE);
            holder.quantityTv.setVisibility(View.VISIBLE);
        }
        else {
            holder.reduceBt.setVisibility(View.GONE);
            holder.quantityTv.setVisibility(View.GONE);}
*/
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onAddItem(item);
            }
        });
        holder.reduceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callback.onReduceItem(item);

            }
        });

    }

    @Override
    public int getItemCount() {
        return shoplist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_shopcard_content)
        TextView contentTv;
        @BindView(R.id.tv_shopcard_cost)
        TextView costTv;
        @BindView(R.id.tv_shopcard_title)
        TextView titleTv;
        @BindView(R.id.bt_shopcard_reduce)
        ImageButton reduceBt;
        @BindView(R.id.bt_shopcrad_add)
        ImageButton addButton;
        @BindView(R.id.tv_shopcard_quantity)
        TextView quantityTv;
        @BindView(R.id.iv_shopcard_product)
        ImageView productIv;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view=itemView;

        }
    }
}
