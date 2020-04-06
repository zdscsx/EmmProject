package com.example.emmproject.ui.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmproject.R;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.example.emmproject.ui.main.MainActivity;
import com.example.emmproject.utils.MapUtils;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.tencentmap.mapsdk.map.Projection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkListAdapter extends RecyclerView.Adapter<MarkListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MarkLocationBean> markList;
    private Projection mProjection;
    private LatLng mCurrentLatLng;
    public MarkListAdapter(Context context, ArrayList<MarkLocationBean> arrayList) {
        this.context = context;
        markList=arrayList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setmProjection(Projection mProjection) {
        this.mProjection = mProjection;
    }

    public void setmCurrentLatLng(LatLng mCurrentLatLng) {
        this.mCurrentLatLng = mCurrentLatLng;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marklocation,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarkLocationBean markLocationBean=markList.get(position);
        LatLng latLng=new LatLng(Double.parseDouble(markLocationBean.getLat()),Double.parseDouble(markLocationBean.getLng()));
        holder.btGo.setOnClickListener(o->{
            if (!MapUtils.startMapActivity(context,markLocationBean.getAddress()))

               MapUtils.startDownLoadMapApp(context);
        });
        holder.item.setOnClickListener(o-> MainActivity.startActivity(context,markLocationBean));
        holder.tvLocation.setText(markLocationBean.getAddress());
        holder.tvName.setText(markLocationBean.getName());
        holder.tvWorktime.setText(markLocationBean.getWorkBeginTime()+"~"+markLocationBean.getWorkEndTime());

        if (mCurrentLatLng!=null){
            markLocationBean.setDistance(Math.round( mProjection.distanceBetween(latLng,mCurrentLatLng)/1000*100)/100);
            holder.tvDistance.setText("距离: "+markLocationBean.getDistance()+" km");
        }
    }

    @Override
    public int getItemCount() {
        return markList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_marklocation_go)
        ImageView btGo;
        @BindView(R.id.tv_marklocatin_location)
        TextView tvLocation;
        @BindView(R.id.tv_marklocatin_name)
        TextView tvName;
        @BindView(R.id.tv_marklocation_worktime)
        TextView tvWorktime;
        @BindView(R.id.tv_marklocation_ditance)
        TextView tvDistance;
        View item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            item=itemView;
        }
    }
}
