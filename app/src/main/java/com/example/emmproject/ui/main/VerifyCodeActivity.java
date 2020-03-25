package com.example.emmproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import com.example.emmproject.R;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.main.VerifyCodeContract;
import com.example.emmproject.presenter.main.VerifyCodePresenter;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.widget.TimeLimitView;
import com.longer.verifyedittext.PhoneCode;

import butterknife.BindView;
import butterknife.OnClick;

public class VerifyCodeActivity extends BaseActivity<VerifyCodePresenter> implements VerifyCodeContract.View {

    @BindView(R.id.tv_verify_phone)
    TextView mTvPhone;
    @BindView(R.id.code_verifyedit_verify)
    PhoneCode phoneCode;
    @BindView(R.id.tv_verify_send)
    TimeLimitView sendTv;

    private boolean firstClick=true;
    private String mPhone;
    private String message;
    @OnClick({R.id.tv_verify_send ,R.id.ibt_toolbar_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_verify_send :   //调用performclick 时会调用onClickListener.onCLick
                if (firstClick) {        //这里是为了让第一次调用performClick不会请求verifycode
                    firstClick=false;    //因为已经在loginActivity调用过了
                }
                else {
                    mPresenter.getVreifyCode(mPhone);
                }
                break;
            case R.id.ibt_toolbar_back :
                finish();



        }

    }


    public static void startActivity(Context context,String phone,String message){
        Intent intent=new Intent(context,VerifyCodeActivity.class);
        intent.putExtra("phone",phone);
        intent.putExtra("message",message);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_code;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {
       mPhone=getIntent().getStringExtra("phone"); //获取手机号
       message=getIntent().getStringExtra("message");
       mTvPhone.setText("发送验证码");
       sendTv.performClick();
       phoneCode.setOnVCodeCompleteListener(new PhoneCode.OnVCodeInputListener(){
           @Override
           public void vCodeComplete(String verificationCode) {
               mPresenter.verifyCode(mPhone,verificationCode,message);
           }
           @Override
           public void vCodeIncomplete(String verificationCode) { }
       });
    }

    @Override
    public void verifySucceed() {

        MainActivity.startActicity(this);
    }

    @Override
    public void verifyFail() {

    }

    @Override
    public void getCodeSuccess(String message) {
        this.message=message;

    }
}
