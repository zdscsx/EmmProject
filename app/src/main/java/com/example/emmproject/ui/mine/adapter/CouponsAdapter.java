package com.example.emmproject.ui.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmproject.R;
import com.example.emmproject.core.bean.mine.CouponsBean;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {


    private ArrayList<CouponsBean> mCouponsBeans;

    public CouponsAdapter(ArrayList<CouponsBean> couponsBeans) {
        mCouponsBeans = couponsBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupons,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AtomicBoolean moreHasSelect= new AtomicBoolean(false);
        CouponsBean couponsBean=mCouponsBeans.get(position);

     /*   switch (couponsBean.getCouponType()){
            case Constants.COUPONTYPE_REDUCE:
                holder.tvCouponsName.setText("满减卷");
                break;
            case Constants.COUPONSTYPE_CASH:
                holder.tvCouponsName.setText("现金卷");

                break;
            case Constants.COUPONSTYPE_FOOD:
                holder.tvCouponsName.setText("食品卷");

                break;
        }*/
     holder.tvCouponsName.setText(couponsBean.getCouponName());

        holder.tvTip.setOnClickListener(o-> {  //这个没啥就是 那个使用规则旁边的三角标
            {if (moreHasSelect.get())    {
                holder.tvExplain.setVisibility(View.GONE);
                holder.ivMore.setImageResource(R.mipmap.imageview_down);
            }
            else {
                holder.tvExplain.setVisibility(View.VISIBLE);
                holder.ivMore.setImageResource(R.mipmap.imageview_up);
            }
            moreHasSelect.set(!moreHasSelect.get());}
        }
        );

        holder.tvValidTime.setText(couponsBean.getValidStartTime()+" - "+couponsBean.getValidEndTime());
        holder.tvExplain.setText(couponsBean.getDescription());
        holder.tvExplain.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mCouponsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_couponslist_more)
        ImageView ivMore;
        @BindView(R.id.tv_couponslist_explain)
        TextView tvExplain;
        @BindView(R.id.tv_couponslist_name)
        TextView tvCouponsName;
        @BindView(R.id.tv_couponslist_validtime)
        TextView tvValidTime;
        @BindView(R.id.tv_couponslist_tip)
        TextView tvTip;
       /* @BindView(R.id.tv_couponslist_discounts)
        TextView tvDiscounts;*/


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
