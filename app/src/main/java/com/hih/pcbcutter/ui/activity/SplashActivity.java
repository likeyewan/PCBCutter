package com.hih.pcbcutter.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.hih.pcbcutter.tcp.ApplicationUtil;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2020/7/22 8:44.
 **/
public class SplashActivity extends AppCompatActivity implements ApplicationUtil.CListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationUtil appUtil =  (ApplicationUtil) SplashActivity.this.getApplication();
        appUtil.setOnCListener(this);
        try {
            appUtil.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            //耗时任务，比如加载网络数据
           runOnUiThread(() -> {
                // 这里可以睡几秒钟，如果要放广告的话
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //appUtil.sendData("初始化");
            });
        }).start();
    }

    @Override
    public void onConnectionSucceeded() {
        Intent intent = new Intent(SplashActivity.this, HIHActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);//去掉Activity切换间的动画
        SplashActivity.this.finish();
    }

    @Override
    public void onReceiveData(int flag, String data) {
    }
    @Override
    public void onConnectError() {
        Intent intent = new Intent(SplashActivity.this, ReconActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);//去掉Activity切换间的动画
        SplashActivity.this.finish();
    }
    @Override
    public void onBrokenPipe() {
    }

    @Override
    public void onDisconnect() {
    }
}
