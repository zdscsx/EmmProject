package com.example.emmproject.contract.order;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.order.PayRequestBean;
import com.example.emmproject.core.bean.order.WechatPayBean;

/**
 * 说明：提交订单
 * 作者：c1024sx
 * 添加时间：2020/4/1
 */
public interface SubmitOrderContract {
    interface View extends AbstractView {

        void toPay(WechatPayBean wechatPayBean);
    }

    interface Presenter extends AbstractPresenter<View>{
        String getPhone();

        void startPayRequest(PayRequestBean payRequestBean);

    }

}
