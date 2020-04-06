package com.example.emmproject.base.activity;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.presenter.BasePresenter;

/**
 * @author quchao
 * @date 2018/3/30
 */

public abstract class BaseRootActivity<T extends BasePresenter> extends BaseActivity<T> {

    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    private View mErrorView;
    private View mLoadingView;
    private ViewGroup mNormalView;
    private int currentState = NORMAL_STATE;

    @Override
    protected void initEventAndData() {
        mNormalView = (ViewGroup) findViewById(getNornalId());
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup.");
        }
        ViewGroup mParent = (ViewGroup) mNormalView.getParent();
        View.inflate(this, R.layout.error_view, mParent);
        mErrorView = mParent.findViewById(R.id.error_group);
        TextView reloadTv = mErrorView.findViewById(R.id.error_reload_tv);
        reloadTv.setOnClickListener(v -> reload());
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);
    }



    public abstract int getNornalId();
    public abstract void reload();




   /* @Override
    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }
*/
    private void hideCurrentView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        switch (currentState) {
            case NORMAL_STATE:
                mNormalView.setVisibility(View.GONE);
                break;

            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
            default:
                break;
        }
    }
}