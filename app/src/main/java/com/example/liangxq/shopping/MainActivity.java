package com.example.liangxq.shopping;

import android.os.Bundle;
import android.util.Log;

import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.httplibrary.utils.LogUtils;
import com.example.liangxq.shopping.httpdemo.ergediandian.ErGeBean;
import com.example.liangxq.shopping.httpdemo.shopping.ShopDemo;
import com.example.liangxq.shopping.httpdemo.wanandroid.Demo;
import com.example.liangxq.shopping.httpdemo.wanandroid.HttpCallBack;
import com.example.liangxq.shopping.httpdemo.wanandroid.LoginBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //   wanAndrindData();

        erGeData();

        //shopData();

      //  wanLogin(); //玩安卓 登录接口


    }

    private void wanLogin() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username","xujunran");
        map.put("password","123456");
        new HttpClient.Builder()
                .setApiUrl("user/login")
                .post()
                .setJsonBody("1",false)
                .setParamser(map)
                .build()
                .request(new HttpCallBack<LoginBean>() {

                    @Override
                    public void onError(String message, int code) {
                        Log.e("111","message:"+message);
                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                          Log.e("111",loginBean.toString());
                    }

                    @Override
                    public LoginBean convert(JsonElement result) {
                        return new Gson().fromJson(new Gson().toJson(result),LoginBean.class);
                    }
                });

    }

    private void shopData() {
       new HttpClient.Builder()
                .setApiUrl("category/getCategory")
                .post()
                //怎么转换成json
                .setJsonBody("{\"parentId\":\"0\"}",true)
                //数据的回调要注意要理解
                .build().request(new HttpCallBack<List<ShopDemo>>() {
            @Override
            public void onError(String message, int code) {

            }

            @Override
            public void cancle() {

            }

            @Override
            public void onSuccess(List<ShopDemo> shopDemos) {
             Log.e("111",shopDemos.toString());
            }

            @Override
            public List<ShopDemo> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result,ShopDemo.class);
            }
        });
    }

    private void erGeData() {
        new HttpClient.Builder()
                .setApiUrl("getUpgrade")
                .post()
                .setJsonBody("{\"parentId\":\"0\"}",true)
                .build()
                .request(new HttpCallBack<ErGeBean>() {

                    @Override
                    public void onError(String message, int code) {
                        Log.e("111",message+code);

                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    public void onSuccess(ErGeBean erGeBean) {
                         Log.e("111",erGeBean.toString());
                    }

                    @Override
                    public ErGeBean convert(JsonElement result) {
                        return new Gson().fromJson(result,ErGeBean.class);
                    }
                });
    }

    private void wanAndrindData() {
         new HttpClient.Builder()
                 .setApiUrl("project/list/1/json?cid=294")
                 .get()
                 .build()
                 .request(new HttpCallBack<Demo>() {

                     @Override
                     public void onError(String message, int code) {
                         Log.e("111","message:"+message+" code:"+code);
                     }

                     @Override
                     public void cancle() {

                     }

                     @Override
                     public void onSuccess(Demo demo) {
                          Log.e("111","onSuccess:"+demo.toString());
                     }

                     @Override
                     public Demo convert(JsonElement result) {
                         return new Gson().fromJson(new Gson().toJson(result),Demo.class);
                     }
                 });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
