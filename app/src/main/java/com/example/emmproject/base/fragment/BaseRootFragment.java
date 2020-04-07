package com.example.emmproject.base.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.utils.LogUtils;


/**
 * @author quchao
 * @date 2018/3/30
 */

public abstract class BaseRootFragment<T extends BasePresenter> extends BaseFragment<T> {

    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    private View mErrorView;
    private ViewGroup mNormalView;

    private int currentState = NORMAL_STATE;

    @Override
    protected void initEventAndData() {
        if (getView() == null) {
            return;
        }
        LogUtils.logd("init    fragment");

        mNormalView = getView().findViewById(getNornalId());
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(mFragmentActivity, R.layout.error_view, parent);
        mErrorView = parent.findViewById(R.id.error_group);
        TextView reloadTv = mErrorView.findViewById(R.id.error_reload_tv);
        reloadTv.setOnClickListener(v -> reload());
        mErrorView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);
    }



    public abstract int getNornalId();
    public abstract void reload();

    @Override
    public void showError() {
        LogUtils.logd("showError");
        if (currentState == ERROR_STATE) {
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        LogUtils.logd(mNormalView+"  "+mErrorView);
        switch (currentState) {
            case NORMAL_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.INVISIBLE);
                break;

            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
            default:
                break;
        }
    }
}
