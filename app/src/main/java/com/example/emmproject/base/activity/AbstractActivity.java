package com.example.emmproject.base.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emmproject.ActivityCollector;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbstractActivity extends AppCompatActivity {

    private Unbinder unbinder;
    protected AbstractActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder= ButterKnife.bind(this);
        mActivity=this;
        ActivityCollector.getInstance().addActivity(this);
        onViewCreated();
        initEventAndData();
        initToolbar();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if (unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }

    }
    /**
     * 在initEventAndData()之前执行
     */
    protected abstract void onViewCreated();

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化ToolBar
     */
    protected abstract void initToolbar();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();

}
