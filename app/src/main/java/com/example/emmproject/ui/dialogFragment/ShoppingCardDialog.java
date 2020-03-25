package com.example.emmproject.ui.dialogFragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmproject.contract.order.OrderFragmentContract;
import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.example.emmproject.ui.order.adapter.ShoppingCardAdapter;

import java.util.ArrayList;

public class ShoppingCardDialog extends DialogFragment implements OrderFragmentContract.ItemChangeCallback<ElemeGroupedItem> {

    RecyclerView recyclerView;
    ArrayList<ElemeGroupedItem> shoplist;
    ShoppingCardAdapter shoppingCardAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onAddItem(ElemeGroupedItem elemeGroupedItem) {

    }

    @Override
    public void onReduceItem(ElemeGroupedItem elemeGroupedItem) {

    }
   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_shopcard,container,false);
        initRecyclerview(view);
        shoplist=new ArrayList<>();
        return view;
    }

   private void initRecyclerview(View view){
        recyclerView=view.findViewById(R.id.rv_order_shoplist);
        shoppingCardAdapter= new ShoppingCardAdapter(shoplist);
        recyclerView.setAdapter(shoppingCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



    @Override
    public void onAddItem(ElemeGroupedItem elemeGroupedItem) {
        shoplist.add(elemeGroupedItem);
        if (shoppingCardAdapter!=null)
        shoppingCardAdapter.notifyDataSetChanged();

    }

    @Override
    public void onReduceItem(ElemeGroupedItem elemeGroupedItem) {
        shoplist.remove(elemeGroupedItem);
        if (shoppingCardAdapter!=null)
        shoppingCardAdapter.notifyDataSetChanged();
    }*/
}
