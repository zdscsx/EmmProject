package com.example.emmproject.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.emmproject.R;
import com.example.emmproject.utils.LogUtils;

/**
 * 说明： 限制linearLayout的最大宽高
 * 作者：c1024sx
 * 添加时间：2020/3/25
 */
public class LimitSizeLinearLayout extends LinearLayout {

    int maxHeight;
    int maxWidth;
    public LimitSizeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.LimitSizeLinearLayout);
        maxHeight=typedArray.getDimensionPixelSize(maxHeight,0);
        maxWidth=typedArray.getDimensionPixelOffset(maxWidth,0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.logd(getMeasuredWidth()+"  "+getMeasuredHeight());
        int heightSpecMode= MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasuerMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=getMeasuredWidth();
        int heightSize=getMeasuredHeight();
        if (heightSpecMode==MeasureSpec.AT_MOST&&maxHeight!=0&&heightSize>maxHeight)
            heightSize=maxHeight;
        if (widthMeasuerMode==MeasureSpec.AT_MOST&&maxWidth!=0&&widthSize>maxWidth)
            widthSize=maxWidth;
            setMeasuredDimension(widthSize,heightSize);

    }
}
