package com.example.emmproject.contract.order;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.order.ElemeGroupedItem;
import com.example.emmproject.core.bean.order.PrePayInfoBean;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.kunminx.linkage.bean.BaseGroupedItem;

import java.util.ArrayList;

public interface OrderFragmentContract {

   interface View extends AbstractView{

       void showProduct(ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>> itemInfos);

       void toSubmitActivity(PrePayInfoBean prePayInfoBean);
   }

   interface Presenter extends AbstractPresenter<View>{

       void getProduct(int storeId);

       void submitOrder(int chargeId,ArrayList<ShopCardFoodBean> shopCardFoodBeans );

   }

   interface ItemChangeCallback<T>{
       void onAddItem(T t);

       void onReduceItem(T t);
   }

}
