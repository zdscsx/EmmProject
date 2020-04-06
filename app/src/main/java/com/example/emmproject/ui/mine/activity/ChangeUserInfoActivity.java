package com.example.emmproject.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.bean.DateType;
import com.example.emmproject.R;
import com.example.emmproject.app.Constants;
import com.example.emmproject.base.activity.BaseActivity;
import com.example.emmproject.contract.mine.ChangeUserInfoContract;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.presenter.mine.ChangeUserInfoPresenter;
import com.example.emmproject.ui.main.MainActivity;
import com.example.emmproject.utils.DateUtils;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.widget.datepicker.CustomDatePicker;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.util.InputInfo;
import com.kongzue.dialog.v3.InputDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.suke.widget.SwitchButton;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ChangeUserInfoActivity extends BaseActivity<ChangeUserInfoPresenter> implements ChangeUserInfoContract.View
{
    @BindView(R.id.tv_changeinfo_name)
    TextView tvName;
    @BindView(R.id.iv_changeinfo_photo)
    ImageView ivPic;
    @BindView(R.id.tv_changeinfo_birthday)
    TextView tvBirthday;
    @BindView(R.id.sw_changeinfo_sex)
    SwitchButton mSwitchSex;
    @BindView(R.id.iv_changeinfo_right)
    ImageView ivBirthRight;

    private User mUser=new User();
    private boolean hasChoiceBirthday;
    @OnClick({R.id.rl_changeinfo_photo,R.id.rl_changeinfo_birthday,R.id.rl_changeinfo_name,R.id.bt_changeinfo_logout,R.id.sw_changeinfo_sex})
    void onClick(View view){
        switch (view.getId()){
            case R.id.rl_changeinfo_photo:
                PictureSelector.create(ChangeUserInfoActivity.this)
                        .openGallery(PictureMimeType.ofImage()).maxSelectNum(1).isCamera(true).compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.rl_changeinfo_birthday:
                if(!hasChoiceBirthday){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
                    String now = sdf.format(new Date());
                    CustomDatePicker customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                        @Override
                        public void onTimeSelected(long timestamp) {
                            String birthday=dateFormat.format(new Date(timestamp));
                            MessageDialog.build(ChangeUserInfoActivity.this)
                                    .setStyle(DialogSettings.STYLE.STYLE_MATERIAL)
                                    .setTheme(DialogSettings.THEME.DARK)
                                    .setTitle("提示")
                                    .setMessage("生日只能设置一次，是否确认修改为："+birthday)
                                    .setOkButton("确定", new OnDialogButtonClickListener() {
                                        @Override
                                        public boolean onClick(BaseDialog baseDialog, View v) {
                                            mUser.setBirthday(birthday);
                                            mPresenter.changeUserInfo(mUser);
                                            return false;
                                        }

                                    })
                                    .setCancelButton("取消", new OnDialogButtonClickListener() {
                                        @Override
                                        public boolean onClick(BaseDialog baseDialog, View v) {
                                            baseDialog.doDismiss();
                                            return false;
                                        }
                                    })
                                    .show();
                        }
                    }, "1970-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
                    customDatePicker1.setCanShowPreciseTime(false); // 不显示时和分
                    customDatePicker1.setScrollLoop(false); // 不允许循环滚动
                    customDatePicker1.show(now);}
                break;
            case R.id.rl_changeinfo_name:
                InputDialog.build(this).setStyle(DialogSettings.STYLE.STYLE_KONGZUE).setCancelButton("取消")
                        .setOkButton("确定")
                        .setTitle("输入昵称")
                        .setMessage("")
                        .setInputInfo(new InputInfo().setInputType(InputType.TYPE_CLASS_TEXT))
                        .setOnOkButtonClickListener(new OnInputDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                                mUser.setUsername(inputStr);
                                mPresenter.changeUserInfo(mUser);
                                return true;
                            }
                        }).show();
                break;
            case R.id.bt_changeinfo_logout:
                mPresenter.logout();
                break;




        }

    }

    public void setYear(DatePickDialog datePickDialog){
        Class dialogClass=DatePickDialog.class;


        try {
            Field field=dialogClass.getDeclaredField("mDatePicker");
            field.setAccessible(true);
            Object datePicker=field.get(datePickDialog);
            Class pickClass=Class.forName("com.codbking.widget.DatePicker");
            Field yearArray= pickClass.getDeclaredField("yearArr");
            yearArray.setAccessible(true);
            int currentYear=DateUtils.getYear(new Date());
            ArrayList<Integer> integers=new ArrayList<>();
            for (int i = currentYear-100;i<=currentYear;i++){
                integers.add(i);
            }
            yearArray.set(datePicker,integers.toArray());


        } catch (Exception e) {
            LogUtils.logd(e.getLocalizedMessage());
            LogUtils.loge(e);
            e.printStackTrace();
        }

    }




    public static void startActivity(Context context){
        Intent intent=new Intent(context,ChangeUserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//修改头像回调
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMediaList= PictureSelector.obtainMultipleResult(data);//获取选择照片
                    mPresenter.uploadPic(localMediaList.get(0));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_user_info;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

        mPresenter.getUser();
        mSwitchSex.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked){                //选择男{
                    mUser.setGender(Constants.SEX_MAN);
                }
                else {
                    mUser.setGender(Constants.SEX_WOMWN);
                }
                mPresenter.changeUserInfo(mUser);
            }
        });
    }


    @Override
    public void changePicSuccess() {

    }

    @Override
    public void showUser(User user) {
        mUser=user;
        tvName.setText(mUser.getUsername());
        Glide.with(this).applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(mUser.getAvatar())
                .placeholder(R.mipmap.imageview_defaultavatar)
                .error(R.mipmap.imageview_defaultavatar).into(ivPic);
        if (!TextUtils.isEmpty(user.getBirthday())) {
            ivBirthRight.setVisibility(View.INVISIBLE); //生日右边的箭头，设置了生日就隐藏
            hasChoiceBirthday = true;
        }
        tvBirthday.setText(hasChoiceBirthday?mUser.getBirthday():"未设置");

        mSwitchSex.setChecked(mUser.isMan());

    }

    @Override
    public void logoutSuccess() {
        MainActivity.startActicity(this);
    }

}
