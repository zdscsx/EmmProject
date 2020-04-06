package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.ChangeUserInfoContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.entity.LocalMedia;

import javax.inject.Inject;

public class ChangeUserInfoPresenter extends BasePresenter<ChangeUserInfoContract.View> implements ChangeUserInfoContract.Presenter {
   private DataManager dataManager;

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
                mView.showUser(userBaseResponse.getData());

            }

        }));
    }

    @Override
    public void changeUserInfo(User user) {

        User user1=new User();
        user1.setUsername(user.getUsername());
        user1.setUserId(user.getUserId());
        user1.setGender(user.getGender());
        user1.setBirthday(user.getBirthday());

        addSubscribe(dataManager.changeInfo(user1).compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new TipObserver<User>(mView,"更新用户信息失败"){
            @Override
            public void onSucceed(BaseResponse<User> userBaseResponse) {
                super.onSucceed(userBaseResponse);
               dataManager.saveUser(user1);
               mView.showUser(user1);
            }

            @Override
            public void onFail(String cause) {
                super.onFail(cause);
                mView.showUser(dataManager.getUser());//更新失败显示数据库中的user
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
}
