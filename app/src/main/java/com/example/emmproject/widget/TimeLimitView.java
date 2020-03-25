package com.example.emmproject.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TimeLimitView extends TextView implements Runnable{
    /** 倒计时秒数 */
    private int mTotalSecond = 60;
    /** 秒数单位文本 */
    private static final String TIME_UNIT = "S";

    /** 当前秒数 */
    private int mCurrentSecond;
    /** 记录原有的文本 */
    private CharSequence mRecordText;
    /** 标记是否重置了倒计控件 */
    private boolean mFlag;

    public TimeLimitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setmTotalSecond(int mTotalSecond) {
        this.mTotalSecond = mTotalSecond;
    }

    @Override
    public boolean performClick() {
        setEnabled(false);
        mRecordText=getText();
        mCurrentSecond=mTotalSecond;
        post(this);
        return super.performClick();
    }

    @Override
    public void run() {
        if (mCurrentSecond==1){
            setEnabled(true);
            setText("发送验证码");
        }
        else {
            mCurrentSecond--;
            setText(mCurrentSecond + " " + TIME_UNIT);
            postDelayed(this, 1000);
        }
    }
}
