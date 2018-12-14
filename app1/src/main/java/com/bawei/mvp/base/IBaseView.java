package com.bawei.mvp.base;

/**
 * Created by 小哥 on 2018/9/22.
 */

public interface IBaseView<V> {

    public void onSuccess(V reulst);

    public void onError();

}
