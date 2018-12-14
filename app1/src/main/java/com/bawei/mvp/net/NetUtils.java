package com.bawei.mvp.net;

import android.os.Handler;
import android.os.Looper;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 小哥 on 2018/9/22.
 */

public class NetUtils {
    private static final NetUtils ourInstance = new NetUtils();
    private OkHttpClient okHttpClient;

    public static NetUtils getInstance() {
        return ourInstance;
    }

    private Handler mhandler;

    {
        okHttpClient = new OkHttpClient
                .Builder()
                .build();
        mhandler = new Handler(Looper.getMainLooper());
    }
    private NetUtils() {
    }
    public void get(String url, final Callback callback){
        final Request request = new Request
                .Builder()
                .url(url)
                .build();
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                if (okHttpClient !=null){
                    okHttpClient.newCall(request).enqueue(callback);
                }
            }
        });
    }
    public void post(String url, FormBody formBody,final Callback callback){
        final Request request = new Request
                .Builder()
                .post(formBody)
                .url(url)
                .build();
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                if (okHttpClient !=null){
                    okHttpClient.newCall(request).enqueue(callback);
                }
            }
        });
    }
}
