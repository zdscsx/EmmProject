package com.example.emmproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.main.LoginContract;
import com.example.emmproject.core.bean.LoginByPasswordBean;
import com.example.emmproject.presenter.main.LoginPresenter;
import com.example.emmproject.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.bt_login_login)
    Button mBtLogin;
    @BindView(R.id.tv_login_question)
    TextView mTvQuestion;
    @BindView(R.id.tv_login_change)
    TextView mTvChange;
    @BindView(R.id.et_login_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_login_tip)
    TextView mTvTip;
    @BindView(R.id.rl_login_password)
    RelativeLayout mRlPassowrdLogin;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;

    private String message;
    private boolean isPasswordLoad=false;
    @OnClick({R.id.bt_login_login,R.id.tv_login_change,R.id.ibt_toolbar_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.bt_login_login :
                if (isPasswordLoad) {
                    mPresenter.loginByPassword(new LoginByPasswordBean(mEtPhone.getText().toString(),mEtLoginPassword.getText().toString()));
                }
                else {
                    mPresenter.getVreifyCode(mEtPhone.getText().toString());
                }
                break;
            case R.id.tv_login_change:
                isPasswordLoad=!isPasswordLoad;//修改登录模式
                setView(isPasswordLoad);
                break;
            case R.id.ibt_toolbar_back :
                finish();
        }
    }

    void setView(boolean isPasswordLoad){

        if (!isPasswordLoad) {
            mRlPassowrdLogin.setVisibility(View.GONE);
            mTvTip.setVisibility(View.VISIBLE);
            mBtLogin.setText("获取短信验证码");
            mTvChange.setText("密码登陆");
            mBtLogin.setOnClickListener(o->{

            });
        }
        else {
            mRlPassowrdLogin.setVisibility(View.VISIBLE);
            mTvTip.setVisibility(View.GONE);
            mBtLogin.setText("登录");
            mTvChange.setText("验证码登录");

        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,LoginActivity.class));
    }

    @OnTextChanged(value = R.id.et_login_phone,callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onPhoneInput(CharSequence s,int start,int berfore,int count){
        if (s.length()<11) //手机号长度为11位
        {   mBtLogin.setClickable(false);
            mBtLogin.setBackground(getResources().getDrawable(R.drawable.bg_button_grey));
        }
        else {
            mBtLogin.setClickable(true);
            mBtLogin.setBackground(getResources().getDrawable(R.drawable.bg_login_button));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {
        mBtLogin.setClickable(false);
    }

    @Override
    public void loginSuccess() {
        MainActivity.startActicity(this);
    }

    @Override
    public void loginFail() {
        LogUtils.logd("shibai");
    }

    @Override
    public void getCodeSuccess(String message) {
        VerifyCodeActivity.startActivity(this,mEtPhone.getText().toString(),message);//跳转验证
    }




}
