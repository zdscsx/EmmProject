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

    int widthSpec;
    int heightSpec;
    public LimitSizeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initValue(context,attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (widthSpec==0&&heightSpec==0){
            widthSpec=widthMeasureSpec;
            heightSpec=heightMeasureSpec;
        }
        int heightSpecMode= MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasuerMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=getMeasuredWidth();
        int heightSize=getMeasuredHeight();
        if (heightSpecMode==MeasureSpec.AT_MOST&&maxHeight!=0&&heightSize>maxHeight)
        {
            getLayoutParams().height=maxHeight;
            heightSize=maxHeight;
        }
        if (widthMeasuerMode==MeasureSpec.AT_MOST&&maxWidth!=0&&widthSize>maxWidth)
        {            getLayoutParams().width=maxWidth;
                     widthSize=maxWidth;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);
        setLayoutParams(widthSpec,heightSpec);
    }

    void setLayoutParams(int width,int height){

        getLayoutParams().width=width;
        getLayoutParams().height=height;
    }

    void initValue(Context context,AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.LimitSizeLinearLayout);
        maxHeight=typedArray.getDimensionPixelSize(R.styleable.LimitSizeLinearLayout_maxHeight,0);
        maxWidth=typedArray.getDimensionPixelOffset(R.styleable.LimitSizeLinearLayout_maxWidth,0);

    }
}
