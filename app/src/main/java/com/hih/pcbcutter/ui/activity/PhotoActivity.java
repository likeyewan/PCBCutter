package com.hih.pcbcutter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.hih.pcbcutter.Cameras.CameraSurfaceHolder;
import com.hih.pcbcutter.R;

import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.ui.adapter.DataAdapter;
import com.hih.pcbcutter.ui.bean.DataSet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PhotoActivity extends AppCompatActivity implements ApplicationUtil.TestListener,SurfaceHolder.Callback {
    private ApplicationUtil appUtil;
    private final List<DataSet> dataSetList = new ArrayList<>();
    private SurfaceView surfaceView;
    private final CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();


    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        appUtil=(ApplicationUtil)this.getApplication();
        appUtil.setOnTestListener(this);
        appUtil.sendData("图像参数");
        surfaceView = (SurfaceView) findViewById(R.id.main_sf_camera);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        mCameraSurfaceHolder.setCameraSurfaceHolder(PhotoActivity.this,surfaceView);
    }
    private void init() {
        RecyclerView recyclerView =findViewById(R.id.record_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PhotoActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        DataAdapter adapter = new DataAdapter(dataSetList, appUtil);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onReceiveData(int flag, String data) {
        String[] str = data.split(";");
        if (str[0].equals("图像参数")) {
            String[][] strings = new String[str.length][];
            for (int i = 1; i < str.length; i++) {
                strings[i - 1] = str[i].split(",");
            }
            for (int i = 0; i < 9; i++) {
                DataSet dataSet = new DataSet();
                String[] strt = {"亮度", "对比度", "色度", "饱和度", "锐度", "灰度低值", "灰度高值", "轮廓大小", "搜索级别"};
                dataSet.setName(strt[i]);
                dataSet.setNum(strings[i + 2][1]);
                dataSetList.add(dataSet);
            }
            init();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
