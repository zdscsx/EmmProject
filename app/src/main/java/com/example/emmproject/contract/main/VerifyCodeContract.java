package com.example.emmproject.contract.main;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;

public interface VerifyCodeContract {
   interface View extends AbstractView{

      void verifySucceed();

      void verifyFail();

      void getCodeSuccess(String message);

   }

   interface Presenter extends AbstractPresenter<View>{
      void getVreifyCode(String phoneNumber);

       void verifyCode(String code,String phone,String message);


   }

}
