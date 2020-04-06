package com.example.emmproject.presenter.main;

import com.example.emmproject.app.Constants;
import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.main.LoginContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.main.LoginByPasswordBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{
    DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getVreifyCode(String phoneNumber) {
      addSubscribe(mDataManager.getVerifyCode(phoneNumber)
              .compose(RxUtils.rxSchedulerHelper())
              .subscribeWith(new TipObserver<String>(mView,"获取验证码失败"){
            @Override
            public void onNext(BaseResponse<String> baseResponse) {
                super.onNext(baseResponse);
                mView.getCodeSuccess(baseResponse.getMessage());
            }

          @Override
          public void onError(Throwable e) {
              super.onError(e);

              mView.showToast(e.getCause().toString());
          }
      }));
    }

    @Override
    public void wechatLogin() {

    }

    @Override
    public void loginByPassword(LoginByPasswordBean loginByPasswordBean) {

       addSubscribe(mDataManager.loginByPassword(loginByPasswordBean).compose(RxUtils.rxSchedulerHelper())
               .subscribeWith(new ResourceObserver<Response<BaseResponse<User>>>(){

           @Override
           public void onNext(Response<BaseResponse<User>> userResponse) {

               if (userResponse.body().getCode()== Constants.STSTUS_SUCCESS) //登陆成功保存token和user信息
               {   mDataManager.saveUser(userResponse.body().getData());
                   mDataManager.saveTooken(userResponse.headers().get("token"));
                   mDataManager.savaRefreshToken(userResponse.headers().get("refresh_token"));
                   mDataManager.setUesrPhone(userResponse.body().getData().getPhone());
                   mView.loginSuccess();
               }
               else {
                   mView.loginFail();
                   mView.showToast(userResponse.message());
               }
           }

           @Override
           public void onError(Throwable e) {
               mView.loginFail();
               mView.showToast(e.getCause().toString()+" "+e.getMessage());
           }

           @Override
           public void onComplete() {

           }
       }));

    }


}
