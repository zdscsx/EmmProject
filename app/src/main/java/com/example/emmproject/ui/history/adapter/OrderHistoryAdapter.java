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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.app.Constants;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.core.bean.history.OrderInfoBean;
import com.example.emmproject.core.bean.order.FoodBean;
import com.example.emmproject.ui.history.activity.OrderInfoActivity;
import com.example.emmproject.utils.LogUtils;

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
        OrderInfoBean orderInfoBean=orderHistoryBean.getOrderInfo();
        OrderHistoryBean.ChargeInfoBean chargeInfoBean=orderHistoryBean.getChargeInfo();
        holder.view.setOnClickListener(o-> OrderInfoActivity.startActivity(mContext,orderHistoryBean));
        FoodBean foodBean=orderHistoryBean.getFoodList()[0];
        LogUtils.logd(chargeInfoBean.getAddress()+chargeInfoBean.getLat()+chargeInfoBean.getName()+"testlocation");
        RoundedCorners roundedCorners=new RoundedCorners(Constants.ROUND_CORNERS);
        RequestOptions requestOptions=new RequestOptions().transforms(new CenterCrop(),roundedCorners);
        Glide.with(mContext).load(foodBean.getMainImage()).placeholder(R.mipmap.imageview_defaultavatar)
                .apply(requestOptions).into(holder.ivPic);
        holder.tvFoodName.setText(foodBean.getFoodName());
        holder.tvLocation.setText(chargeInfoBean.getName());
        holder.tvTime.setText(orderInfoBean.getCreateTime());
        holder.tvQuantity.setText("共"+orderHistoryBean.getFoodList().length+"件");
        holder.tvCost.setText("实付: ￥"+orderInfoBean.getTotalPrice());
        String status;
        switch (orderInfoBean.getBusinessStatus()){
            case Constants.ORDER_STATUS_FINISH: status="已完成";
            break;
            case Constants.ORDER_STATUS_CANCEL :status="已取消";
            break;
            case Constants.ORDER_STATUS_WAITPAY: status="等待支付";
            break;
             case Constants.ORDER_STATUS_WAITAKE:status="等待取餐";
            break;
            default:status="已完成";
        }
        holder.tvState.setText(status);

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
       /* @BindView(R.id.tv_orderhistory_option)
        TextView tvOption;*/
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
