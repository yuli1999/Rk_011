package com.bawei.mvp.contract;

import com.bawei.mvp.base.BasePresenter;

/**
 * Created by 小哥 on 2018/9/22.
 */

public interface MvpContract {

    public interface BaseModel{
        public void getConnData(ICallBack iCallBack);
    }
    public abstract class IPresenter extends BasePresenter{
        public abstract void getData();
    }
}
