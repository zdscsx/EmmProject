package com.example.emmproject.presenter.mine;

import com.example.emmproject.base.presenter.BasePresenter;
import com.example.emmproject.contract.mine.MineFragmentContract;
import com.example.emmproject.core.BaseObserver;
import com.example.emmproject.core.BaseResponse;
import com.example.emmproject.core.DataManager;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.utils.LogUtils;
import com.example.emmproject.utils.RxUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineFragmentContract.View> implements MineFragmentContract.Presenter {

    DataManager mDataManager;

    @Inject
    public MinePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getUser() {
        User user=mDataManager.getUser();

        if (user==null){//用户还没有登陆 显示登陆view
            mView.showLoginView();
        }else {
            addSubscribe(mDataManager.getUserFormHttp().compose(RxUtils.rxSchedulerHelper()).subscribeWith(new BaseObserver<User>(mView) {
                @Override
                public void onSucceed(BaseResponse<User> userBaseResponse) {
                    super.onSucceed(userBaseResponse);
                    mDataManager.saveUser(userBaseResponse.getData());
                }
            }));
            mView.showUserInfo(mDataManager.getUser());
        }
    }
}
