package com.example.liangxq.shopping.app;

import android.app.Application;

import com.example.httplibrary.HttpConstant;
import com.example.httplibrary.HttpGlobalConfig;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.app
 * 文件名：ShopApplication
 * 创建者：liangxq
 * 创建时间：2020/8/1  14:12
 * 描述：TODO
 */
public class ShopApplication extends Application {
    String BASE_WANANZUO="https://www.wanandroid.com/";
    String BASE_SHOP="http://169.254.39.119:8080/kotlin/";
    String BASE_ERGEDD="http://api.t.ergedd.com/";
    //getUpgrade

    @Override
    public void onCreate() {
        super.onCreate();
        HttpGlobalConfig.getInsance()
               .setBaseUrl(BASE_ERGEDD)
                .setTimeout(HttpConstant.TIME_OUT)
                .setShowLog(true)
                .setTimeUnit(HttpConstant.TIME_UNIT)
                .initReady(this);
    }
}
