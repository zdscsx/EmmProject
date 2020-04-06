package com.example.emmproject.presenter.main;

import com.example.emmproject.app.Constants;
import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.main.VerifyCodeContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.TipObserver;
import com.example.emmproject.core.bean.main.LoginBean;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;
import retrofit2.Response;

public class VerifyCodePresenter extends BasePresenter<VerifyCodeContract.View > implements VerifyCodeContract.Presenter {
    DataManager mDataManager;

    @Inject
    public VerifyCodePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getVreifyCode(String phoneNumber) {


      addSubscribe( mDataManager.getVerifyCode(phoneNumber).compose(RxUtils.rxSchedulerHelper()).
               subscribeWith(new TipObserver(mView,"获取验证码失败"){
                   @Override
                   public void onSucceed(BaseResponse baseResponse) {
                       super.onSucceed(baseResponse);
                       try {
                           mView.getCodeSuccess(baseResponse.getMessage());
                       } catch (Exception e){
                           LogUtils.loge(e);
                       }

                   }
       }));}

    @Override
    public void verifyCode(String phone,String code,String message) {
        LoginBean loginBean=new LoginBean(phone,code,message);
        addSubscribe(mDataManager.login(loginBean)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new ResourceObserver<Response<BaseResponse<User>>>(){
                    @Override
                    public void onNext(Response<BaseResponse<User>> userResponse) {
                        if (userResponse.body().getCode()== Constants.STSTUS_SUCCESS)
                        {
                            mDataManager.saveUser(userResponse.body().getData());
                            mDataManager.setUesrPhone(userResponse.body().getData().getPhone());
                            mDataManager.saveTooken(userResponse.headers().get("token"));
                            mDataManager.savaRefreshToken(userResponse.headers().get("refresh_token"));
                            mView.verifySucceed();
                        }
                        else {
                            mView.showToast(userResponse.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.verifyFail();
                        mView.showToast(e.getCause()+" "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
