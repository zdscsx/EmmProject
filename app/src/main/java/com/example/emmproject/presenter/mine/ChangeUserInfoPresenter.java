package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.ChangeUserInfoContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.mine.ChangeUserinfoBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.ui.main.LoginActivity;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.entity.LocalMedia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;
import retrofit2.Response;

public class ChangeUserInfoPresenter extends BasePresenter<ChangeUserInfoContract.View> implements ChangeUserInfoContract.Presenter {
   private DataManager dataManager;
   private User mUser;
   @Inject
    public ChangeUserInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void uploadPic(LocalMedia pic) {
       addSubscribe(dataManager.changePic(pic).compose(RxUtils.rxSchedulerHelper())
               .subscribeWith(new TipObserver<String>(mView){
                   @Override
                   public void onSucceed(BaseResponse<String> stringBaseResponse) {
                       super.onSucceed(stringBaseResponse);
                       mView.changePicSuccess();
                       User user=dataManager.getUser();
                       user.setAvatar(stringBaseResponse.getData());
                       dataManager.saveUser(user);
                       mView.showUser(user);
                   }

                   @Override
                   public void onFail(String cause) {
                       super.onFail(cause);
                   }
               }));
    }

    @Override
    public void getUser() {
        addSubscribe(dataManager.getUserFormHttp().compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver<User>(mView){
            @Override
            public void onSucceed(BaseResponse<User> userBaseResponse) {
                mUser=userBaseResponse.getData();
                mView.showUser(mUser);
                dataManager.saveUser(mUser);



            }

        }));
    }


    @Override
    public void logout() {
       addSubscribe( dataManager.logout()
               .compose(RxUtils.rxSchedulerHelper())
               .subscribeWith(new TipObserver(mView){

                   @Override
                   public void onSucceed(BaseResponse baseResponse) {
                       super.onSucceed(baseResponse);
                       mView.logoutSuccess();
                       dataManager.cleanData();
                   }

                   @Override
                   public void onFail(String cause) {
                       super.onFail(cause);
                       mView.logoutSuccess();
                       dataManager.cleanData();
                   }
               }));
    }

    @Override
    public void changeUserInfo(User user) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        if(user.equals(dataManager.getUser()))//跟原数据一样不用上传修改，直接返回
            return;
        ChangeUserinfoBean requsetBean=new ChangeUserinfoBean();
        requsetBean.setUsername(user.getUsername());
        requsetBean.setUserId(user.getUserId());
        requsetBean.setGender(user.getGender());
        submitRequest(requsetBean);
        mUser=user;
    }

    @Override
    public void changeBirthday(long time) {
        ChangeUserinfoBean requsetBean=new ChangeUserinfoBean();
        requsetBean.setBirthday(time);
        requsetBean.setUserId(mUser.getUserId());
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        String day = dateFormat.format(new Date(time));
        mUser.setBirthday(day);
        submitRequest(requsetBean);
    }

    private void submitRequest(ChangeUserinfoBean changeUserinfoBean){
        addSubscribe(dataManager.changeInfo(changeUserinfoBean)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new TipObserver(mView,"修改用户信息失败"){
                    @Override
                    public void onSucceed(BaseResponse userBaseResponse) {
                        super.onSucceed(userBaseResponse);
                        dataManager.saveUser(mUser);
                        mView.showUser(mUser);
                    }

                    @Override
                    public void onFail(String cause) {
                        super.onFail(cause);
                        mUser=dataManager.getUser();
                        mView.showUser(mUser);//更新失败显示数据库中的user
                    }
                }));
    }
}
