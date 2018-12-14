package com.bawei.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 小哥 on 2018/9/23.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
