package com.example.emmproject.ui.mine.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.UserInfoContract;
import com.example.emmproject.core.bean.history.HistoryIntegralBean;
import com.example.emmproject.core.bean.mine.IntegralBean;
import com.example.emmproject.presenter.mine.UserInfoPresenter;
import com.example.emmproject.ui.mine.adapter.IntrgralAdapter;
import com.example.emmproject.utils.CommonUtils;
import com.example.emmproject.utils.DialogUtils;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.widget.MyBounceInterpolator;
import com.flyco.tablayout.CommonTabLayout;
import com.kongzue.dialog.v3.CustomDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import ezy.ui.view.RoundButton;
import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter > implements UserInfoContract.View {


    @BindView(R.id.pb_userinfo_integral)
    ZzHorizontalProgressBar mProgressBar;
    @BindView(R.id.tv_userinfo_integral_big)
    TextView tvBigIntegral;
    @BindView(R.id.tv_userinfo_integral_small)
    TextView tvSamllIntegral;
    @BindView(R.id.tv_userinfo_integral)
    TextView tvIntegral;
    @BindView(R.id.rv_userinfo_integral)
    RecyclerView rvIntegralList;
    @BindView(R.id.tv_userinfo_get)
    TextView tvGet;
    @BindView(R.id.view_userinfo_indicator)
    android.view.View indicator;
    @BindView(R.id.ly_empty)
    LinearLayout lyEmpty;
    @BindView(R.id.tv_empty_tip)
     TextView tvEmpty;



    boolean isSelectGet=true;
    private IntrgralAdapter mIntrgralAdapter;
    private ArrayList<HistoryIntegralBean >mIntegralBeans;

    @OnClick({R.id.rl_userinfo_birthday,R.id.rl_userinfo_year,R.id.ly_userinfo_edit,R.id.tv_userinfo_rule,R.id.tv_userinfo_get,R.id.tv_userinfo_reduce})
    void onClick(View  view){
        switch (view.getId()){
            case R.id.rl_userinfo_birthday:
                DialogUtils.showExplain(this,"生日饮品券","用户可在\"个人资料”中设置自己的生日，以后每年每逄生日当天，系统将自动下发- -张生日专属礼物券，可用于抵扣在本平台_上的消费。生日券有效期自下发之时起为一个月，过期作废。\n" +
                        "\n" +
                        "注意，生日只能设置-次，设置之后将无法更改，请填写您的真实信息，以保证能正常享受本平台的服务及以后的各项活动。.\n" +
                        "\n" +
                        "有任何疑问请咨询充电场站工作人员。");
                break;
            case R.id.rl_userinfo_year:
                DialogUtils.showExplain(this,"周年庆饮品券","每年12月25号，系统将自动下发- -张周年庆专属礼物券，可用于抵扣在本平台.上的消费。周年庆礼品券有效期自下发之时起为一个月，过期作废。\n" +
                        "\n" +
                        "周年庆活动可能因实际情况适时调整，请关注平台通知。\n" +
                        "\n" +
                        "有任何疑问请咨询充电场站工作人员。");
                break;
            case R.id.ly_userinfo_edit:
                ChangeUserInfoActivity.startActivity(this);
                break;
            case R.id.tv_userinfo_rule:

            case R.id.tv_userinfo_get:
                if (!isSelectGet){
                    startAniamtionToX(0);
                }
                mPresenter.queryIntrgalHistory();
                isSelectGet=true;
                break;
             case R.id.tv_userinfo_reduce:
                 showEmpty();
                 if (isSelectGet){
                     startAniamtionToX(tvGet.getMeasuredWidth());
                 }
                 isSelectGet=false;
                 break;
        }

    }
    void startAniamtionToX(float x){
        ValueAnimator valueAnimator= ObjectAnimator.ofFloat(indicator,"translationX",x);
        valueAnimator.setInterpolator(new MyBounceInterpolator(0.2,10));
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    public static void startActivity(Context context){
        Intent intent=new Intent(context,UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {
        mIntegralBeans=new ArrayList<>();
        mIntrgralAdapter = new IntrgralAdapter(mIntegralBeans);
        rvIntegralList.setLayoutManager(new LinearLayoutManager(this));
         rvIntegralList.setAdapter(mIntrgralAdapter);
        mPresenter.getIntegral();
        mPresenter.queryIntrgalHistory();
    }


    @Override
    public void showIntegral(IntegralBean integralBean) {

            String integral=integralBean.getIntegral();
            String covertIntegral=integralBean.getCovertIntegral();
            tvIntegral.setText(integral);
            String[] cintegral =integralBean.getCovertIntegral().split("\\.");
            tvBigIntegral.setText(cintegral[0]+".");
            tvSamllIntegral.setText(cintegral[1]);
            integral= integral=integral.replace(",","");
            covertIntegral= covertIntegral.replace(",","");
            mProgressBar.setMax((int) (CommonUtils.stringToFloat(integral)*100));
            mProgressBar.setProgress((int)(CommonUtils.stringToFloat(integral)*100)-(int) CommonUtils.stringToFloat(covertIntegral)*100);


    }

    @Override
    public void showIntrgalList(ArrayList<HistoryIntegralBean> integralBeans) {
        rvIntegralList.setVisibility(View.VISIBLE);
        lyEmpty.setVisibility(View.GONE);
        mIntegralBeans.clear();
        mIntegralBeans.addAll(integralBeans);
        mIntrgralAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {

        lyEmpty.setVisibility(View.VISIBLE);
        tvEmpty.setText("暂无积分记录");
        rvIntegralList.setVisibility(View.GONE);
    }
}
