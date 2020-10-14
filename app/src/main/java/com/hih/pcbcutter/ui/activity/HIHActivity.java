package com.hih.pcbcutter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.pic.other.PicActivity;
import com.hih.pcbcutter.tcp.ActivityCollector;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.ui.bean.ErrorMsg;
import com.hih.pcbcutter.ui.bean.ProductRecord;
import com.shoulashou.demo.ui.adapter.ErrorMsgAdapter1;
import com.shoulashou.demo.ui.adapter.ProductRecordAdapter1;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likeye on 2020/9/16 17:07.
 **/
public class HIHActivity extends BaseActivity implements ApplicationUtil.CListener {
    @BindView(R.id.robot_num)
    TextView robotNum;
    @BindView(R.id.robot_type)
    TextView robotType;
    @BindView(R.id.program)
    TextView program;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.btn_set)
    Button btnSet;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.pro_record)
    RecyclerView proRecord;
    @BindView(R.id.alarm_record)
    RecyclerView alarmRecord;
    @BindView(R.id.axis_record)
    RecyclerView axisRecord;
    private ApplicationUtil appUtil;
    @BindView(R.id.btn_pic)
    Button btnPic;
    private final List<ProductRecord> list = new ArrayList<>();
    private final List<ErrorMsg> errorMsgs = new ArrayList<>();
    private ProductRecordAdapter1 proAdapter;
    private ErrorMsgAdapter1 errAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置是否显示标题栏
        setShowTitle(false);
        //设置是否显示状态栏
        setShowStatusBar(false);
        //是否允许屏幕旋转
        setAllowScreenRoate(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.act_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initE();
        initR();
    }

    @Override
    protected void initData() {
        appUtil = (ApplicationUtil) this.getApplication();
        appUtil.setOnCListener(this);
    }

    private void initR() {
        for (int i = 0; i < 10; i++) {
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
            String str1 = formatter1.format(curDate);
            String str2 = formatter2.format(curDate);
            //String str = formatter.format(curDate);
            ProductRecord productRecord = new ProductRecord();
            productRecord.setMessage("记录" + i);
            productRecord.setId(i);
            productRecord.setDate(str1);
            productRecord.setStart(str2);
            productRecord.setEnd(str2);
            list.add(productRecord);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        proRecord.setLayoutManager(layoutManager);
        proAdapter = new ProductRecordAdapter1(list);
        proRecord.setAdapter(proAdapter);
    }

    private void initE() {
        for (int i = 0; i < 10; i++) {
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String str = formatter.format(curDate);
            ErrorMsg errorMsg = new ErrorMsg();
            errorMsg.setMsg("记录" + i);
            errorMsg.setNum(i + "");
            errorMsg.setTime(str);
            errorMsgs.add(errorMsg);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        alarmRecord.setLayoutManager(layoutManager);
        errAdapter = new ErrorMsgAdapter1(errorMsgs);
        alarmRecord.setAdapter(errAdapter);
    }


    @OnClick({R.id.start, R.id.stop, R.id.reset, R.id.btn_set, R.id.btn_exit,R.id.btn_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                break;
            case R.id.stop:
                break;
            case R.id.reset:
                break;
            case R.id.btn_set:
                Intent intent = new Intent(HIHActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_exit:
                ActivityCollector.finishAll();
                break;
            case R.id.btn_pic:
                Intent intent1=new Intent(HIHActivity.this, PicActivity.class);
                startActivity(intent1);
        }
    }

    private boolean isExit;

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                new Handler().postDelayed(() -> isExit = false, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConnectionSucceeded() {

    }

    @Override
    public void onReceiveData(int flag, String data) {

    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onBrokenPipe() {
        Log.d("ss","sd");
       // Toast.makeText(HIHActivity.this,"断开连接",Toast.LENGTH_SHORT).show();
        appUtil.init();
    }

    @Override
    public void onDisconnect() {

    }
}
