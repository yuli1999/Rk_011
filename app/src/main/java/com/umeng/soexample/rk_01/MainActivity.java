package com.umeng.soexample.rk_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mlv;
    private String url = "http://www.zhaoapi.cn/product/getCatagory";
    private List<Bean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlv = findViewById(R.id.lv);
        initdata();
    }

    private void initdata() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new HttpUtil().get(url).Result(new HttpUtil.HttpListener() {
                    @Override
                    public void Onsuccess(String data) {

                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(data, Bean.class);
                        list.addAll(bean.getData());
                    }
                });
            }
        });

    }
}
