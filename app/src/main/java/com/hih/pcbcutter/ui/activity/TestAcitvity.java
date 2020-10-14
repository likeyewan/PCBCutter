package com.hih.pcbcutter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAcitvity extends AppCompatActivity implements ApplicationUtil.ConnectListener {

    @BindView(R.id.tv)
    TextView textView;
    @BindView(R.id.se)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        ApplicationUtil appUtil=(ApplicationUtil) this.getApplication();
        appUtil.setOnConnectListener(this);

        button.setOnClickListener(v -> appUtil.sendData("likeye"));
    }



    @Override
    public void onReceiveData(int flag,String data) {
        Log.d("ss","data="+data);
        textView.setText(data);
    }


}
