package com.example.emmproject.app;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.emmproject.R;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig;

/**
 * Create by 早点睡 at 20/1/12
 */
public class ShopConfig  implements ILinkagePrimaryAdapterConfig {

    Context mContext;

    static  public    ShopConfig shopConfig;

   public static ShopConfig getInstance(){
       if (shopConfig==null)
       {
           synchronized (ShopConfig.class){
               if (shopConfig==null)
                   shopConfig=new ShopConfig();
           }

       }
       return shopConfig;
   }

    @Override
    public void setContext(Context context) {
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop;
    }

    @Override
    public int getGroupTitleViewId() {
        return R.id.tv_group_shop;
    }

    @Override
    public int getRootViewId() {
        return R.id.lv_group_shop;
    }

    @Override
    public void onBindViewHolder(LinkagePrimaryViewHolder holder, boolean selected, String title) {
        ((TextView) holder.mGroupTitle).setText(title);
        if (selected){
           // holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
            holder.getView(R.id.blue_view).setVisibility(View.VISIBLE);
            ((TextView) holder.mGroupTitle).setTextColor(mContext.getResources().getColor(R.color.blue));

        }
        else{
            ((TextView) holder.mGroupTitle).setTextColor(mContext.getResources().getColor(R.color.little_black));
          //  holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.getView(R.id.blue_view).setVisibility(View.GONE);
        }

    }

    @Override
    public void onItemClick(LinkagePrimaryViewHolder holder, View view, String title) {

    }




}
