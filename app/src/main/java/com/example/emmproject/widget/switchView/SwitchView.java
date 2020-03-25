package com.example.emmproject.widget.switchView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.emmproject.utils.CommonUtils;

/**
 * 说明： 双选择控件
 * 作者： c1024sx
 * 添加时间： 2020/03/24
 */
public class SwitchView extends View{

    private int mRadiusX;
    private int mRadisuY;
    private int mWidth;
    private int mHeight;


    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth=getWidth();
        mHeight=getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){

        }
        return true;
    }
}
