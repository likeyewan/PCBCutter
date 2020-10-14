package com.hih.pcbcutter.ui.fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2020/7/22 9:56.
 **/
public class RunFragment extends Fragment implements ApplicationUtil.ConnectListener {
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    @BindView(R.id.start)
    ImageButton start;
    @BindView(R.id.pause)
    ImageButton pause;
    @BindView(R.id.stop)
    ImageButton stop;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.sLanguage)
    Spinner sLanguage;
    @BindView(R.id.edit_msg)
    EditText editMsg;
    @BindView(R.id.bt_send)
    Button btSend;
    private ApplicationUtil appUtil;

    private boolean startPress = false;
    private boolean pausePress = false;
    private boolean stopPress = false;
    private View mView;
    private boolean isFirstLoad = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_run, container, false);
        ButterKnife.bind(this, mView);
        init();
        return mView;
    }

    //初始化
    private void init() {
        pause.setClickable(false);
        tvMessage.setMovementMethod(new ScrollingMovementMethod());
        appUtil = (ApplicationUtil) getActivity().getApplication();
        appUtil.setOnConnectListener(this);
        appUtil.sendData("译放");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appUtil.sendData("初始化");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appUtil.sendData("系统参数");
    }

    @OnClick({R.id.start, R.id.pause, R.id.stop,R.id.bt_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start: {
                appUtil.sendData("开始");
                if (!startPress) {
                    startPress = true;
                    start.setBackgroundResource(R.drawable.ic_play_black_on_24dp);
                    pausePress = false;
                    pause.setBackgroundResource(R.drawable.ic_pause);
                    start.setClickable(false);
                    pause.setClickable(true);
                } else {
                    startPress = false;
                    start.setBackgroundResource(R.drawable.ic_play_black_24dp);

                }
            }
            break;
            case R.id.pause: {
                appUtil.sendData("暂停");
                if (!pausePress) {
                    pausePress = true;
                    pause.setBackgroundResource(R.drawable.ic_pause_on);
                    startPress = false;
                    start.setBackgroundResource(R.drawable.ic_play_black_24dp);
                    start.setClickable(true);
                    pause.setClickable(false);
                } else {
                    pausePress = false;
                    pause.setBackgroundResource(R.drawable.ic_pause);
                }
            }
            break;
            case R.id.stop: {
                appUtil.sendData("停止");
                if (!stopPress) {
                    stopPress = true;
                    startPress = false;
                    start.setBackgroundResource(R.drawable.ic_play_black_24dp);
                    pausePress = false;
                    pause.setBackgroundResource(R.drawable.ic_pause);
                    start.setClickable(false);
                    pause.setClickable(false);
                    stop.setBackgroundResource(R.drawable.ic_stop_on);
                } else {
                    start.setClickable(true);
                    stopPress = false;
                    stop.setBackgroundResource(R.drawable.ic_stop_black_24dp);
                }
            }
            break;
            case R.id.bt_send:
                String str=editMsg.getText().toString();
                appUtil.sendData(str);
                editMsg.setText("");
                break;
        }
    }

    //下拉列表框
    private void spinnerModel(String[] strings, Spinner spinner) {
        //将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_spinner_item, strings);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int position, long id) {
                if (position == 0) {
                    return;
                }
                if (spinner == sLanguage)
                    appUtil.sendData("语言:" + strings[position]);
                if (spinner == spinner1)
                    appUtil.sendData("motion厂家:" + strings[position]);
                if (spinner == spinner2)
                    appUtil.sendData("motion类型:" + strings[position]);
                if (spinner == spinner3)
                    appUtil.sendData("motion规格:" + strings[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReceiveData(int flag, String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        if (flag == 100) {
            //获取标识词
            String[] a = data.split(";");

            String[][] b = new String[a.length][];
            if (a[0].equals("初始化")) {
                for (int i = 0; i < a.length; i++) {
                    b[i] = a[i].split(",");
                }
                String[][] c = new String[b[1].length][1];
                for (int i = 0; i < b[1].length; i++) {
                    c[i][0] = b[1][i];
                }
                spinnerModel(c[0], sLanguage);
                spinnerModel(c[1], spinner1);
                spinnerModel(c[2], spinner2);
                spinnerModel(c[3], spinner3);
            }
            tvMessage.append("\n" + str + "_" + "服务端:" + data);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFirstLoad) {
            appUtil.setOnConnectListener(this);
        }
        if (isVisibleToUser) {
            isFirstLoad = false;
        }
    }
}