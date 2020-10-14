package com.hih.pcbcutter.ui.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.tcp.Constants;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.tcp.SocketService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

/**
 * Created by likeye on 2020/9/22 11:49.
 **/
public class ReconActivity extends BaseActivity implements ApplicationUtil.CListener, SocketService.socketListener {
    @BindView(R.id.ip_et)
    EditText ipEt;
    @BindView(R.id.port_et)
    EditText portEt;
    @BindView(R.id.con_btn)
    Button conBtn;
    private ApplicationUtil appUtil;
    private SharedPreferences sp;
    @BindView(R.id.test_btn)
    Button testBtn;
    private ServiceConnection sc;
    public SocketService socketService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置是否显示标题栏
        setShowTitle(false);
        //设置是否显示状态栏
        setShowStatusBar(false);
        //是否允许屏幕旋转
        setAllowScreenRoate(true);
        super.onCreate(savedInstanceState);
       // bindSocketService();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_reconnect;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        requestMyPermissions();
        sp = getSharedPreferences("socket", Context.MODE_PRIVATE);
        ipEt.setText(sp.getString("ip", ""));
        portEt.setText(sp.getString("port", ""));
        appUtil = (ApplicationUtil) this.getApplication();
        appUtil.setOnCListener(this);
        testBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ReconActivity.this, HIHActivity.class);
            startActivity(intent);
        });
    }
    //动态申请权限
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(ReconActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Log.d(TAG, "requestMyPermissions: 有写SD权限");
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(ReconActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            Log.d(TAG, "requestMyPermissions: 有读SD权限");
        }
    }
    @OnClick(R.id.con_btn)
    public void onViewClicked() {
        bindSocketService();
       /* String ip = ipEt.getText().toString().trim();
        String port = portEt.getText().toString().trim();
        if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(port)) {
            Toast.makeText(this, "ip和端口号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("ip", ip);
        ed.putString("port", port);
        ed.commit();
        //appUtil.setSocket(ip, Integer.parseInt(port));
       // appUtil.init();
        /*启动service*/
        /*Intent intent = new Intent(getApplicationContext(), SocketService.class);
        intent.putExtra(Constants.INTENT_IP, ip);
        intent.putExtra(Constants.INTENT_PORT, port);
        startService(intent);
        /*先判断 Service是否正在运行 如果正在运行  给出提示  防止启动多个service*/
        //if (isServiceRunning("com.shoulashou.demo.tcp.SocketService")) {
        ///    Toast.makeText(this, "连接服务已运行", Toast.LENGTH_SHORT).show();
         //   return;
     //   }

    }
    private void bindSocketService() {
        /*通过binder拿到service*/
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SocketService.SocketBinder binder = (SocketService.SocketBinder) iBinder;
                socketService = binder.getService();
                socketService.setListener(ReconActivity.this);
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        String ip = ipEt.getText().toString().trim();
        String port = portEt.getText().toString().trim();
        if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(port)) {
            Toast.makeText(this, "ip和端口号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), SocketService.class);
        intent.putExtra(Constants.INTENT_IP, ip);
        intent.putExtra(Constants.INTENT_PORT, port);
        bindService(intent, sc, BIND_AUTO_CREATE);
    }
    /**
     * 判断服务是否运行
     */
    private boolean isServiceRunning(final String className) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> info = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningServiceInfo aInfo : info) {
            if (className.equals(aInfo.service.getClassName())) return true;
        }
        return false;
    }
    @Override
    public void onConnectionSucceeded() {
        Intent intent = new Intent(ReconActivity.this, HIHActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);//去掉Activity切换间的动画
        ReconActivity.this.finish();
    }

    @Override
    public void onReceiveData(int flag, String data) {

    }

    @Override
    public void onConnectError() {
        LogUtil.d("ss", "连接失败");
    }

    @Override
    public void onBrokenPipe() {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onConSucceeded() {
        Log.d("sd","d");
        Intent intent = new Intent(ReconActivity.this, HIHActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);//去掉Activity切换间的动画
        ReconActivity.this.finish();
    }
}
