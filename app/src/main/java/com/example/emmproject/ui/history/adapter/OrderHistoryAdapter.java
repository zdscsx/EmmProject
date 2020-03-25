package com.example.emmproject.ui.history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.emmproject.R;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.ui.history.activity.OrderInfoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ezy.ui.view.RoundButton;

public class OrderHistoryAdapter extends RecyclerView.Adapter <OrderHistoryAdapter.ViewHolder >{

   private Context mContext;
   private ArrayList<OrderHistoryBean> mHistoryBeans;



    public OrderHistoryAdapter(ArrayList<OrderHistoryBean> historyBeans,Context context) {
        this.mContext=context;
        this.mHistoryBeans = historyBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderHistoryBean orderHistoryBean=mHistoryBeans.get(position);
        holder.view.setOnClickListener(o-> OrderInfoActivity.startActivity(mContext));
       // Glide.with(mContext).load(orderHistoryBean.get)
    }

    @Override
    public int getItemCount() {
        return mHistoryBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.bt_orderhistory_more)
        RoundButton btMore;
        @BindView(R.id.iv_orderhistory_pic)
        ImageView ivPic;
        @BindView(R.id.tv_orderhistory_cost)
        TextView tvCost;
        @BindView(R.id.tv_orderhistory_foodname)
        TextView tvFoodName;
        @BindView(R.id.tv_orderhistory_location)
        TextView tvLocation;
        @BindView(R.id.tv_orderhistory_option)
        TextView tvOption;
        @BindView(R.id.tv_orderhistory_quantity)
        TextView tvQuantity;
        @BindView(R.id.tv_orderhistory_state)
        TextView tvState;
        @BindView(R.id.tv_orderhistory_time)
        TextView tvTime;
        View  view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view=itemView;
        }
    }
}
