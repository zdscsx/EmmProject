package com.example.emmproject.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.example.emmproject.core.bean.order.ShopCardFoodBean;
import com.example.emmproject.core.bean.order.StoreFoodBean;
import com.google.gson.Gson;
import com.kongzue.dialog.v3.CustomDialog;

import java.util.ArrayList;

import ezy.ui.view.RoundButton;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DialogUtils {

    public static void showExplain(AppCompatActivity context, String title, String content){
        CustomDialog.show(context, R.layout.dialog_explain, new CustomDialog.OnBindView() {
            @Override
            public void onBind(CustomDialog dialog, View v) {
                v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                RoundButton confirmBt=v.findViewById(R.id.rbt_diaolog_dismiss);
                TextView titleTv=v.findViewById(R.id.tv_dialog_title);
                TextView contentTv=v.findViewById(R.id.tv_dialog_explain);
                titleTv.setText(title);
                contentTv.setText(content);
                confirmBt.setOnClickListener(o-> dialog.doDismiss());

            }

        }).setAlign(CustomDialog.ALIGN.BOTTOM);
    }
    //show
    //代码坏味道



}
