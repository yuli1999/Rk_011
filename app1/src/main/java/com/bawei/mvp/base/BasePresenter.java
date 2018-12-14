package com.bawei.mvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by 小哥 on 2018/9/22.
 */

public abstract class BasePresenter<V extends  IBaseView> {

    private WeakReference<V> weakReference;

    public void onAttachView(V v){
        weakReference = new WeakReference(v);
    }
    public void onDestory(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference = null;
        }
    }
    public V getView(){
        return weakReference.get();
    }
    public boolean isAttachView(){
        return weakReference.get() !=null && weakReference !=null;
    }
}
