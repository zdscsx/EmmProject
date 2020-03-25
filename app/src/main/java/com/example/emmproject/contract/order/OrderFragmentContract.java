package com.example.emmproject.contract.order;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.kunminx.linkage.bean.BaseGroupedItem;

import java.util.ArrayList;

public interface OrderFragmentContract {

   interface View extends AbstractView{

       void showProduct(ArrayList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>> itemInfos);
   }

   interface Presenter extends AbstractPresenter<View>{

       void getProduct(int storeId);
   }

   interface ItemChangeCallback<T>{
       void onAddItem(T t);

       void onReduceItem(T t);
   }

}
