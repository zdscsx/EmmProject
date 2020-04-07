package com.example.emmproject.ui.history.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmproject.R;
import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.base.fragment.BaseFragment;
import com.example.emmproject.contract.history.HistoryContract;
import com.example.emmproject.core.bean.history.OrderHistoryBean;
import com.example.emmproject.presenter.history.HistoryPresenter;
import com.example.emmproject.ui.history.adapter.OrderHistoryAdapter;
import com.example.emmproject.ui.mine.MineFragment;
import com.example.emmproject.ui.order.OrderFragment;
import com.example.emmproject.utils.LogUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HistoryFragment extends BaseFragment<HistoryPresenter>  implements HistoryContract.View {

    @BindView(R.id.tab_history_order)
    SegmentTabLayout tab;
    @BindView(R.id.rv_history_orderlist)
    RecyclerView historyRecyclerView;
    @BindView(R.id.ibt_toolbar_back)
    ImageButton back;
    @BindView(R.id.tv_toolbar_title)
    TextView mTvTitle;
    @BindView(R.id.tv_empty_tip)
    TextView tvEmptyTip;
    @BindView(R.id.ly_empty)
    RelativeLayout lyEmpty;

    private int nowPage=0;
    private OrderHistoryAdapter mHihstoryAdapter;
    private ArrayList<OrderHistoryBean> mHistoryBeans;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        tab.setTabData(new String[]{"全部","立等可取","已完成"});//tab标题
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                nowPage=position;
                mHistoryBeans.clear();
                mHihstoryAdapter.notifyDataSetChanged();
                mPresenter.getHistoryList(position);

            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        initRecyclerView();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

    }

    public void getData(){
        mPresenter.getHistoryList(nowPage);
    }

   private void initRecyclerView(){
        mHistoryBeans=new ArrayList<>();
        mHihstoryAdapter=new OrderHistoryAdapter(mHistoryBeans,getActivity());
        historyRecyclerView.setAdapter(mHihstoryAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getHistoryList(nowPage);
        back.setVisibility(View.GONE);
        mTvTitle.setText("历史订单");

    }

    @Override
    public void inject() {
        EmmApplication.getComponent().inject(this); //依赖注入

    }

    @Override
    public void showUnLogin() {
        LogUtils.logd("login");
        lyEmpty.setVisibility(View.VISIBLE);
        tvEmptyTip.setText("登陆后可查看订单");
    }

    @Override
    public void showEmpty() {
        LogUtils.logd("empty");
        lyEmpty.setVisibility(View.VISIBLE);
        tvEmptyTip.setText("您还没订单哦");
    }

    @Override
    public void showHistoryList(ArrayList<OrderHistoryBean> historyBeans) {
        LogUtils.logd("show");
        lyEmpty.setVisibility(View.GONE);
        mHistoryBeans.clear();
        mHistoryBeans.addAll(historyBeans);
        mHihstoryAdapter.notifyDataSetChanged();

    }

}
