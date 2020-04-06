package com.example.emmproject.wxapi;

import android.app.Activity;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    public void onReq(BaseReq baseReq) {

        if (baseReq.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            finish();
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {

    }
}
