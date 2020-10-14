package com.hih.pcbcutter.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.ui.bean.ProductRecord;
import com.hih.pcbcutter.util.ExcelUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by likeye on 2020/8/20 15:47.
 **/
public class SaveExcelActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    private AlertDialog alertDialog;

    private final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private final int REQUEST_PERMISSION_CODE = 1000;

    @SuppressLint("SdCardPath")
    private String filePath = "/storage/emulated/0/headinhead";

    //请求权限
    private void requestPermission() {
        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(SaveExcelActivity.this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
                LogUtil.e("ss","requestPermission:" + "用户之前已经授予了权限！");
            } else {
                requestPermissions(permissions, REQUEST_PERMISSION_CODE);
            }
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_excel);
        requestPermission();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        Button exportButton = findViewById(R.id.export_button);
        exportButton.setOnClickListener(this);

        Button openButton = findViewById(R.id.open_button);
        openButton.setOnClickListener(this);
        textView = findViewById(R.id.textView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LogUtil.e("ss","申请成功");
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SaveExcelActivity.this);
                builder.setTitle("permission");
                builder.setMessage("点击允许才可以使用");
                builder.setPositiveButton("去允许", (dialog, which) -> {
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    ActivityCompat.requestPermissions(SaveExcelActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                });
                alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        }
    }

    private void showDialogTipUserRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.export_button:
                exportExcel(this);
                break;
            case R.id.open_button:
                openDir();
            default:
                break;
        }
    }

    private void openDir() {
        File file = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(Uri.fromFile(file), "file/*");

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "没用正确打开文件管理器", Toast.LENGTH_SHORT).show();
        }
    }

    //导出
    private void exportExcel(Context context) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();

        }
        String excelFileName = "/demo.xls";

        String[] title = {"编号", "日期", "起始时间","结束时间","产量"};
        String sheetName = "生产记录";
        List<ProductRecord>list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
            String str1=formatter1.format(curDate);
            String str2=formatter2.format(curDate);
            //String str = formatter.format(curDate);
            ProductRecord productRecord=new ProductRecord();
            productRecord.setMessage("记录"+i);
            productRecord.setId(i);
            productRecord.setDate(str1);
            productRecord.setStart(str2);
            productRecord.setEnd(str2);
            list.add(productRecord);
        }

        filePath = filePath + excelFileName;//文件的路径
        ExcelUtil.initExcel(filePath, sheetName, title);
        ExcelUtil.writeObjListToExcel(0,list, filePath, context);
        textView.setText("excel已导出: " + filePath);
    }
}

