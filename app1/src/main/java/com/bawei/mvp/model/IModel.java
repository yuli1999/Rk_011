package com.bawei.mvp.model;

import com.bawei.mvp.contract.ICallBack;
import com.bawei.mvp.contract.MvpContract;
import com.bawei.mvp.net.NetUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 小哥 on 2018/9/22.
 */

public class IModel implements MvpContract.BaseModel{
    String url = "https://www.zhaoapi.cn/product/getCarts?uid=71";
    @Override
    public void getConnData(final ICallBack iCallBack) {
        NetUtils.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iCallBack.onErrer();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iCallBack.onSuccess(response.body().string());
            }
        });
    }
}
