package com.example.emmproject.core.bean.order;

import androidx.annotation.Nullable;

import com.example.emmproject.core.bean.order.StoreFoodBean.*;
import com.example.emmproject.core.bean.ElemeGroupedItem;

import java.util.Arrays;
import java.util.List;

public class ShopCardFoodBean {

    private StoreInfoBean storeInfo;
    private FoodListBean foodListBean;
    private int quantity;
    // private List<FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean> selectionsBeans;
    private FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean selectionsBean;
    public ShopCardFoodBean(StoreInfoBean storeInfoBean, FoodListBean foodListBean, FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean selectionsBean) {
        this.storeInfo=storeInfoBean;
        this.foodListBean=foodListBean;
        this.selectionsBean=selectionsBean;
    }
    public ShopCardFoodBean(StoreInfoBean storeInfoBean, FoodListBean foodListBean) {
        this.storeInfo=storeInfoBean;
        this.foodListBean=foodListBean;

    }


    public FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean getSelectionsBeans() {
        return selectionsBean;
    }

    public void setSelectionsBean(FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean selectionsBeans) {
        this.selectionsBean = selectionsBeans;
    }

    public int getQuantity() {
        return quantity;
    }

    public int changeQuantity(boolean isAdd) {
        if (isAdd)
            return ++this.quantity;
        return --this.quantity;

    }

    /* public List<FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean> getSelectionsBeans() {
        return selectionsBeans;
    }

    public void setSelectionsBeans(List<FoodListBean.FoodOptionBean.OptionListBean.SelectionsBean> selectionsBeans) {
        this.selectionsBeans = selectionsBeans;
    }*/

    public FoodListBean getFoodListBean() {
        return foodListBean;
    }

    public void setFoodListBean(FoodListBean foodListBean) {
        this.foodListBean = foodListBean;
    }

    public StoreInfoBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj==this)
            return true;
        if (obj==null)
            return false;
        if (obj.getClass()!=getClass())
            return false;
        ShopCardFoodBean item=(ShopCardFoodBean)obj;

        //判断是否同一条购物车bean
        if (//item.storeInfo.getStoreId()==getStoreInfo().getStoreId()&&
                item.getFoodListBean().getFoodId()==getFoodListBean().getFoodId()
                        &&((item.getSelectionsBeans()==null&&getSelectionsBeans()==null)//两者都为selection为null就是同一类
                        ||((item.getSelectionsBeans()!=null&&getSelectionsBeans()!=null)&&
                        (item.getSelectionsBeans().getSelectId()==getSelectionsBeans().getSelectId()))))
            return true;

        return false;
    }


}
