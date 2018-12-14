package com.umeng.soexample.rk_01;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * data:2018/11/28
 * author: HJL (磊)
 * function:
 */
public class HttpUtil {
    public HttpUtil(){}

    public HttpUtil get(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        String s = new StringBuilder().toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("location charset=utf-8"), s);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().toString();
                listener.Onsuccess(data);
            }
        });

        return this;
    }

    private HttpListener listener;

    public void Result(HttpListener listener){
        this.listener = listener;
    }

    public interface HttpListener{
        void Onsuccess(String data);
    }

}
