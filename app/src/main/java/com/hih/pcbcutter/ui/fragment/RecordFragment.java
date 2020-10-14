package com.hih.pcbcutter.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.ui.adapter.DataAdapter;
import com.hih.pcbcutter.ui.bean.DataSet;
import com.hih.pcbcutter.ui.bean.ErrorMsg;
import com.hih.pcbcutter.ui.bean.ProductRecord;
import com.hih.pcbcutter.util.ExcelUtil;
import com.shoulashou.demo.ui.adapter.ErrorMsgAdapter1;
import com.shoulashou.demo.ui.adapter.ProductRecordAdapter1;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/7/22 9:57.
 **/
public class RecordFragment extends Fragment implements ApplicationUtil.TestListener {
    @BindView(R.id.test)
    RecyclerView test;
    @BindView(R.id.record_list)
    RecyclerView recordList;
    @BindView(R.id.output)
    Button output;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.output_error)
    Button outputError;
    @BindView(R.id.clear_error)
    Button clearError;
    private final List<ProductRecord> list = new ArrayList<>();
    private final List<ErrorMsg> errorMsgs = new ArrayList<>();
    private final List<DataSet> dataSetList = new ArrayList<>();
    boolean isFirstLoad = true;
    private View view;
    private ApplicationUtil appUtil;
    @SuppressLint("SdCardPath")
    private final String filePath = "/storage/emulated/0/HIH";
    private final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private RecyclerView productRecy;
    private ProductRecordAdapter1 proAdapter;
    private ErrorMsgAdapter1 errAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);
        //initR(view);
        appUtil = (ApplicationUtil) getActivity().getApplication();
        appUtil.setOnTestListener(this);
        appUtil.sendData("图像参数");
        initR(view);
        initE();
        init();
        return view;
    }

    //导出生产记录
    private void exportExcel(Context context) {
        LogUtil.d("dd", "cheng1");
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();

        }
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String str = formatter.format(curDate);
        String excelFileName = "/生产记录" + str +".xls";
        String[] title = {"编号", "日期", "起始时间", "结束时间", "产量"};
        String sheetName = "生产记录";


        // filePath = filePath + excelFileName;//文件的路径
        ExcelUtil.initExcel(filePath + excelFileName, sheetName, title);
        ExcelUtil.writeObjListToExcel(0,list, filePath + excelFileName, context);
        LogUtil.d("dd", "cheng");
    }
    //导出报警记录
    private void exportErrorExcel(Context context) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String str = formatter.format(curDate);
        String excelFileName = "/报警记录" + str + ".xls";
        String[] title = {"编号", "日期", "报警记忆录"};
        String sheetName = "报警记录";
        ExcelUtil.initExcel(filePath + excelFileName, sheetName, title);
        ExcelUtil.writeObjListToExcel(1,errorMsgs, filePath + excelFileName, context);
    }
    private void initR(View view) {
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
        productRecy = view.findViewById(R.id.test);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        productRecy.setLayoutManager(layoutManager);
        proAdapter = new ProductRecordAdapter1(list);
        productRecy.setAdapter(proAdapter);
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
        RecyclerView recyclerView = view.findViewById(R.id.error_record);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
         errAdapter= new ErrorMsgAdapter1(errorMsgs);
        recyclerView.setAdapter(errAdapter);
    }

    private void init() {
        RecyclerView recyclerView = view.findViewById(R.id.record_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
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

    @OnClick({R.id.output, R.id.clear,R.id.output_error, R.id.clear_error})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.output:
                requestPermission();
                exportExcel(getContext());
                break;
            case R.id.clear:
                list.clear();
                proAdapter.notifyDataSetChanged();
                break;
            case R.id.output_error:
                exportErrorExcel(getContext());
                break;
            case R.id.clear_error:
                errorMsgs.clear();
                errAdapter.notifyDataSetChanged();
                break;
        }
    }

    //请求权限
    private void requestPermission() {
        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissions[0]) == PackageManager.PERMISSION_GRANTED) {
                LogUtil.e("ss", "requestPermission:" + "用户之前已经授予了权限！");
            } else {
                int REQUEST_PERMISSION_CODE = 1000;
                requestPermissions(permissions, REQUEST_PERMISSION_CODE);
            }
        }
    }

}
