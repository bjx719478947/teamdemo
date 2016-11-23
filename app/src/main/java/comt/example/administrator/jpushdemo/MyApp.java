package comt.example.administrator.jpushdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Author: zwf(zhaoweifeng@1000phone.com)
 * Date  : 2016-11-18
 * Time  : 00:41
 * Project: JPushDemo
 * Descrite:
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
    }
}
