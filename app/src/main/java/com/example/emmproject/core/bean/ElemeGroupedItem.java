package com.example.emmproject.core.bean;

import androidx.annotation.Nullable;

import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.example.emmproject.core.bean.order.StoreFoodBean.FoodListBean;
import com.google.gson.Gson;
import com.kunminx.linkage.bean.BaseGroupedItem;

import java.util.List;


public class ElemeGroupedItem extends BaseGroupedItem<ElemeGroupedItem.ItemInfo> {


    /**
     * storeId : 3
     * storeInfo : {"storeId":3,"name":"公告","address":" 213","introduction":"..","phone":"12341342344","mainImage":"https://kpengidrive.com/charging-order/static/image/store/669192189731848192.png","open":true,"imageList":["https://kpengidrive.com/charging-order/static/image/store/669192189731848192.png"]}
     */

    private StoreFoodBean.StoreInfoBean storeInfoBean;
    private int storeId;


    public ElemeGroupedItem(boolean isHeader, String header) {
            super(isHeader, header);
        }

        public ElemeGroupedItem(ItemInfo item) { super(item); }

    public static ElemeGroupedItem objectFromData(String str) {

        return new Gson().fromJson(str, ElemeGroupedItem.class);
    }



    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }


    public StoreFoodBean.StoreInfoBean getStoreInfoBean() {
        return storeInfoBean;
    }

    public void setStoreInfoBean(StoreFoodBean.StoreInfoBean storeInfoBean) {
        this.storeInfoBean = storeInfoBean;
    }

    public static class ItemInfo extends BaseGroupedItem.ItemInfo {

        FoodListBean foodListBean;


        public ItemInfo(String title, String group, FoodListBean foodListBean) {
            super(title, group);
            this.foodListBean = foodListBean;
        }

        public FoodListBean getFoodListBean() {
            return foodListBean;
        }


        public static ItemInfo objectFromData(String str) {

                return new Gson().fromJson(str, ItemInfo.class);
            }


    }




}


