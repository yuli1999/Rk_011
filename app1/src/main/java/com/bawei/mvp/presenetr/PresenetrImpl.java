package com.bawei.mvp.presenetr;

import com.bawei.mvp.contract.ICallBack;
import com.bawei.mvp.contract.MvpContract;
import com.bawei.mvp.model.IModel;

/**
 * Created by 小哥 on 2018/9/22.
 */

public class PresenetrImpl extends MvpContract.IPresenter {
    private IModel iModel;

    public PresenetrImpl() {
       iModel = new IModel();
    }

    @Override
    public void getData() {
        iModel.getConnData(new ICallBack() {
            @Override
            public void onSuccess(String result) {
                getView().onSuccess(result);
            }

            @Override
            public void onErrer() {
                getView().onError();
            }
        });
    }
}
