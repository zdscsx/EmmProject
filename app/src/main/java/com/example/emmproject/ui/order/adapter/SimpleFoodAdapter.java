package com.example.emmproject.ui.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.core.bean.order.Food;
import com.example.emmproject.core.bean.order.FoodBean;
import com.example.emmproject.core.bean.order.PrePayInfoBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：提交订单里面的adapter
 * 作者： c1024sx
 * 添加时间：2020/4/1
 */
public class SimpleFoodAdapter  extends RecyclerView.Adapter<SimpleFoodAdapter.ViewHolder> {

   private ArrayList<Food> mFoodListBeans;
   private Context mContext;

    public SimpleFoodAdapter(ArrayList<Food> foodListBeans, Context context) {
        mFoodListBeans = foodListBeans;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_submitfood,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Food foodBean=mFoodListBeans.get(position);
        Glide.with(mContext).applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(foodBean.getMainImage())
                .placeholder(R.mipmap.imageview_defaultavatar)
                .error(R.mipmap.imageview_defaultavatar).into(holder.ivPic);
        holder.tvName.setText(foodBean.getFoodName());
        holder.tvPrice.setText(foodBean.getOriginSum()+"");
        holder.tvQuantity.setText(foodBean.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return mFoodListBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_submitfood_pic)
        ImageView ivPic;
        @BindView(R.id.tv_submitfood_name)
        TextView tvName;
        @BindView(R.id.tv_submitfood_price)
        TextView tvPrice;
        @BindView(R.id.tv_submitfood_quantity)
        TextView tvQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
