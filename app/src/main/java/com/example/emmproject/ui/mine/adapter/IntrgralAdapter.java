package com.example.emmproject.ui.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmproject.R;
import com.example.emmproject.app.Constants;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class IntrgralAdapter extends RecyclerView.Adapter <IntrgralAdapter.ViewHolder> {
    ArrayList<HistoryIntegralBean> mIntegralBeans;

    public IntrgralAdapter(ArrayList<HistoryIntegralBean> integralBeans) {
        mIntegralBeans=integralBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_intrgal,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       HistoryIntegralBean integralBean=mIntegralBeans.get(position);
       String integralChange;
       if (integralBean.getStatus()== Constants.INTEGRAL_ADD)
           integralChange="+"+integralBean.getOperate_integral();
       else integralChange="-"+integralBean.getOperate_integral();
       holder.tvTime.setText(integralBean.getOperate_time());
       holder.tvSource.setText(integralBean.getDescription());
       holder.tvChangeCount.setText(integralChange);
    }

    @Override
    public int getItemCount() {
        return mIntegralBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tv_integral_source)
        TextView tvSource;
    @BindView(R.id.tv_integral_time)
    TextView tvTime;
    @BindView(R.id.tv_integral_changecount)
    TextView tvChangeCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
