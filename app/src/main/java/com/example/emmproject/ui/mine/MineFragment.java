package com.example.emmproject.ui.mine;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.emmproject.R;
import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.base.fragment.BaseFragment;
import com.example.emmproject.contract.mine.MineFragmentContract;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.presenter.mine.MinePresenter;
import com.example.emmproject.ui.main.LoginActivity;
import com.example.emmproject.ui.mine.activity.ActivityActivity;
import com.example.emmproject.ui.mine.activity.CouponsActivity;
import com.example.emmproject.ui.mine.activity.UserInfoActivity;
import com.example.emmproject.utils.LogUtils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment<MinePresenter> implements MineFragmentContract.View {

    @BindView(R.id.iv_mine_avater)
    ImageView ivAvater;
    @BindView(R.id.tv_mine_name)
    TextView tvName;
    @BindView(R.id.tv_mine_integral)
    TextView tvIntegeral;


    private boolean isLogin=false;

    @OnClick({R.id.ly_mine_coupons,R.id.ly_mine_userinfo,R.id.ly_mine_activity})
    void onClick(View view){
        if (!isLogin){
            LoginActivity.startActivity(getActivity());
        }
        else {
            switch (view.getId()) {
                case R.id.ly_mine_userinfo:
                    UserInfoActivity.startActivity(getContext());
                    break;
                case R.id.ly_mine_coupons:
                    CouponsActivity.startActivity(getContext());
                    getActivity().overridePendingTransition(R.anim.animation, R.anim.exit_ios_anim);
                    break;
                case R.id.ly_mine_activity:
                    ActivityActivity.startActivity(getContext());
                    break;

            }
        }
    }


    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        if (mPresenter!=null)
            mPresenter.getUser();
        super.onResume();
    }

    static public MineFragment newInstance(){
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEventAndData() {
       mPresenter.getUser();

    }

    @Override
    public void showUserInfo(User user) {
        isLogin=true;
        Glide.with(this).applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(user.getAvatar())
                .placeholder(R.mipmap.imageview_defaultavatar)
                .error(R.mipmap.imageview_defaultavatar).into(ivAvater);
        tvName.setText(user.getUsername());
        tvIntegeral.setText(user.getIntegral());
    }

    @Override
    public void showLoginView() {
        isLogin=false;
        Glide.with(this).applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(R.mipmap.imageview_defaultavatar)
                .placeholder(R.mipmap.imageview_defaultavatar)
                .error(R.mipmap.imageview_defaultavatar).into(ivAvater);
        tvIntegeral.setText("");
        tvName.setText("点击登陆");
        tvName.setTextColor(getContext().getResources().getColor(R.color.blue));

    }

    @Override
    public void inject() {
        EmmApplication.getComponent().inject(this);

    }
}
