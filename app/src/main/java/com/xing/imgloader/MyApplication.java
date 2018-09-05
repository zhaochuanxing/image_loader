package com.xing.imgloader;

import android.app.Application;
import android.content.Context;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function:
 */
public class MyApplication extends Application {

    public static MyApplication sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext(){
        return sContext;
    }
}
