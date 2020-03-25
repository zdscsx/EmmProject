package com.example.emmproject.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.UserInfoContract;
import com.example.emmproject.presenter.mine.UserInfoPresenter;
import com.example.emmproject.utils.DialogUtils;
import com.kongzue.dialog.v3.CustomDialog;

import butterknife.OnClick;
import ezy.ui.view.RoundButton;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter > implements UserInfoContract.View {




    @OnClick({R.id.rl_userinfo_birthday,R.id.rl_userinfo_year,R.id.ly_userinfo_edit,R.id.tv_userinfo_rule})
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
        }

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

    }
}
