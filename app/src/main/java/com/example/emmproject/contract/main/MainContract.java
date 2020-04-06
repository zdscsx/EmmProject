package com.example.emmproject.contract.main;

import com.example.emmproject.base.presenter.AbstractPresenter;
import com.example.emmproject.base.view.AbstractView;
import com.example.emmproject.core.bean.order.MarkLocationBean;
import com.tencent.mapsdk.raster.model.LatLng;

public interface MainContract {

    interface View extends AbstractView{

        void showLocation(MarkLocationBean locationBean);
    }

    interface Presenter extends AbstractPresenter<View>{
        void getMarkLocation(LatLng latLng);

        void refreshToken();
    }
}
