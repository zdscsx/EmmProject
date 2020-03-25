package com.example.emmproject.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.emmproject.R;

public class DividerView extends View {
  private Paint linePaint;
  private int lineColor;
  private float lineHeight;
  private float width;
  private float height;

    public DividerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray= context.obtainStyledAttributes(attrs, R.styleable.DividerView);
        lineColor=typedArray.getColor(R.styleable.DividerView_line_color,Color.BLACK);
        lineHeight=typedArray.getDimension(R.styleable.DividerView_line_height,1);
        typedArray.recycle();
        init();
    }

    void init(){
        linePaint=new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth();
        height=getHeight();
        float pointDividerWidth=width/100;
        for (float i=0;i<width;i+=pointDividerWidth)
        {
            canvas.drawPoint(i,height/2,linePaint);
        }
    }
}
