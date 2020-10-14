package com.hih.pcbcutter.pic.other;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.pic.bean.Line;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PicActivity extends AppCompatActivity implements PicView.ChooseListener{
    @BindView(R.id.typePick)
    ImageView typePick;
    @BindView(R.id.delPick)
    ImageView delPick;
    @BindView(R.id.bili)
    Button bili;
    @BindView(R.id.redraw)
    Button redraw;
    @BindView(R.id.xzb)
    TextView xzb;
    @BindView(R.id.yzb)
    TextView yzb;
    @BindView(R.id.color)
    TextView colorT;
    @BindView(R.id.recover)
    Button recover;
    @BindView(R.id.act_main_mainlayout)
    FrameLayout actMainMainlayout;
    @BindView(R.id.drawer_layout)
    RelativeLayout drawerLayout;
    @BindView(R.id.pen_size)
    TextView penSize;
    @BindView(R.id.decrease)
    TextView decrease;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.pen_type)
    Spinner penType;
    @BindView(R.id.pic_type)
    Spinner picType;
    @BindView(R.id.cx)
    Button cx;
    private int select_paint_type_index = 0;
    private PicView picView;
    private int mTouchMode;
    private PointF mTouchCenterPt;//两指中点坐标
    private float mOldDist, mNewDist;
    private float mToucheCentreXOnGraffiti, mToucheCentreYOnGraffiti;
    private boolean flag = false;
    private int size = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
        try {
            initView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        receive();
    }


    /**
     * 选择颜色的dialog
     */
    private void showPaintColorDialog() {
        ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.show(getSupportFragmentManager(), "dialog_color");
        colorPickerDialog.setOnColorChangeListenter(new ColorPickerDialog.OnColorListener() {
            @Override
            public void onEnsure(int color) {
                if (color == 0) {
                    picView.colPicker = Color.BLACK;
                } else {
                    picView.colPicker = color;
                    colorT.setBackgroundColor(color);
                }
            }

            @Override
            public void onBack() {
            }
        });
        colorPickerDialog.setCancelable(false);
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    private static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() throws FileNotFoundException {
        String[] strings = {"轮廓", "填充"};
        spinnerModel(strings, penType);
        String[] strings1 = {"选中区域", "剔除区域"};
        spinnerModel(strings1, picType);
        FrameLayout mFrameLayout = findViewById(R.id.act_main_mainlayout);
        BitmapFactory.Options options = new BitmapFactory.Options();
        TypedValue value = new TypedValue();
        options.inTargetDensity = value.density;
        options.inScaled = false;
        penSize.setText(size + "");
        Bitmap mSrcBitmap = getLoacalBitmap("/sdcard/test.png"); //从本地取图片(在cdcard中获取)  //
        Log.d("ss", "mw=" + mSrcBitmap.getWidth() + "," + mSrcBitmap.getHeight());
        picView = new PicView(PicActivity.this, mSrcBitmap);
        picView.setChooseListenr(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mFrameLayout.addView(picView, params);
        picView.setOnTouchListener((view, event) -> {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    mTouchMode = 1;
                    if (picView.flag == -1) {
                        mToucheCentreXOnGraffiti = picView.screenToBitmapX(event.getX());
                        mToucheCentreYOnGraffiti = picView.screenToBitmapY(event.getY());
                        picView.saveCurrentScale();
                    }
                    return false;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mTouchMode = 0;
                    flag = false;
                    return false;
                case MotionEvent.ACTION_MOVE:
                    if (mTouchMode == 1 && !flag) {
                        if (picView.flag == -1) {
                            float transX = picView.toTransX(event.getX(), mToucheCentreXOnGraffiti);
                            float transY = picView.toTransY(event.getY(), mToucheCentreYOnGraffiti);
                            picView.setTransScale(1, transX, transY);
                        }
                        return false;
                    }
                    if (mTouchMode == 2) {
                        PointF ptf = getMid(event);
                        mNewDist = spacing(event);// 两点按下时的距离
                        float sc = mNewDist / mOldDist;
                        float transX = picView.toTransX(ptf.x, mToucheCentreXOnGraffiti);
                        float transY = picView.toTransY(ptf.y, mToucheCentreYOnGraffiti);
                        picView.setTransScale(sc, transX, transY);
                        // picView.setScale(sc,ptf);
                    }
                    return true;
                case MotionEvent.ACTION_POINTER_UP:
                    mTouchMode -= 1;
                    if (mTouchMode == 1) {
                        //picView.PointertUp();
                    }
                    return true;
                case MotionEvent.ACTION_POINTER_DOWN:
                    mTouchMode += 1;
                    if (mTouchMode == 2) {
                        flag = true;
                        mTouchCenterPt = getMid(event);
                        mOldDist = spacing(event);// 两点按下时的距离
                        mToucheCentreXOnGraffiti = picView.screenToBitmapX(mTouchCenterPt.x);
                        mToucheCentreYOnGraffiti = picView.screenToBitmapY(mTouchCenterPt.y);
                        picView.saveCurrentScale();
                    }

                    return true;
            }
            return false;
        });
    }

    public void recePic() throws IOException {
        Socket socket = new Socket("192.168.3.240", 9999);
        InputStream inputStream = socket.getInputStream();
//begin 图片接收
//第1步：接收服务端发过来的图片大小
        byte[] picLenBuff = new byte[200];
        int picLen = inputStream.read(picLenBuff);
//将String 转换成 int
        String picLenString = new String(picLenBuff, 0, picLen);
        int getPicLen = Integer.parseInt(picLenString);
//第2步：回馈。向服务端反馈客户端已经接收到图片的大小了，可以开始发图片了
        OutputStream outputStream = socket.getOutputStream();
        String string = "1";
        outputStream.write(string.getBytes());
        outputStream.flush();
//第3步：开始接收图片
        int offset = 0;//定义偏移量
        byte[] bitmapBuff = new byte[getPicLen];//初始化图片缓存

/**********************最主要部分***********************/
        while (offset < getPicLen) {
            int len = inputStream.read(bitmapBuff, offset, getPicLen - offset);
            offset += len;
        }
/**********************最主要部分***********************/

//开始图片解码
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBuff, 0, offset);
//用handler通知UI线程显示图片
        Message message = handler.obtainMessage(1, bitmap);
        handler.sendMessage(message);
    }

    private void receive() {
        new Thread(() -> {
            Socket socket;
            try {
                //这里进行连接服务器，
                socket = new Socket("192.168.3.240", 9999);
                try {
                    //接收文件名
                    InputStream nameStream = socket.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(nameStream);
                    BufferedReader br = new BufferedReader(streamReader);
                    String fileName = br.readLine();
                    br.close();
                    streamReader.close();
                    nameStream.close();
                    socket.close();
                    //接收文件数据
                    socket = new Socket("192.168.3.240", 9999);
                    InputStream dataStream = socket.getInputStream();
                    String savePath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
                    FileOutputStream file = new FileOutputStream(savePath, false);
                    byte[] buffer = new byte[1024];
                    int size = -1;
                    while ((size = dataStream.read(buffer)) != -1) {
                        file.write(buffer, 0, size);
                    }
                    Bitmap bitmap;
                    bitmap = getLoacalBitmap("/sdcard/" + fileName);
                    Log.d("ss", "bitmap=" + bitmap);
                    file.close();
                    dataStream.close();
                    socket.close();
                } catch (Exception ignored) {
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    typePick.setImageBitmap((Bitmap)msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * 计算两指间的距离
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /*取两指的中心点坐标*/
    private PointF getMid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }

    //下拉选择框
    private void spinnerModel(String[] strings, Spinner spinner) {
        //将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PicActivity.this, android.R.layout.simple_spinner_dropdown_item, strings);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int position, long id) {
                if (strings[position].equals("轮廓"))
                    picView.penStyle = Paint.Style.STROKE;
                else if (strings[position].equals("填充"))
                    picView.penStyle = Paint.Style.FILL;
                else if (strings[position].equals("选中区域")) {
                    picView.delChoose = false;
                } else if (strings[position].equals("剔除区域")) {
                    picView.delChoose = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    //弹出画笔类型选项对话框
    private void showPaintTypeDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.custom_dialog);
        alertDialogBuilder.setTitle("选择类型：");
        alertDialogBuilder.setSingleChoiceItems(R.array.painttype, select_paint_type_index, (dialog, which) -> {
            select_paint_type_index = which;
            picView.flag = which + 1;
            if (which == 5 || which == 4) {
                picView.finsh = false;
            }
            dialog.dismiss();
        });
        alertDialogBuilder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.create().show();
    }

    /**
     * 隐藏键盘
     */
    protected void hideKeyboard(View v) {
        ((InputMethodManager) PicActivity.this.getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick({R.id.typePick, R.id.delPick, R.id.bili, R.id.redraw, R.id.color, R.id.decrease, R.id.add, R.id.recover, R.id.cx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.typePick:
                if (select_paint_type_index == 5 || select_paint_type_index == 4) {
                    picView.finsh = true;
                    picView.saveToArr();
                    picView.flag = 0;
                    select_paint_type_index = 0;
                    picView.pointFS = new ArrayList<>();
                } else {
                    showPaintTypeDialog();
                }
                break;
            case R.id.delPick:
                picView.delShape();
                break;
            case R.id.bili:
                picView.flag = 100;
                picView.setTransScale(1, 0, 0);
                break;
            case R.id.redraw:
                //picView.flag = -1;
                picView.reDraw();
                break;
            case R.id.color:
                showPaintColorDialog();
                break;
            case R.id.decrease:
                size--;
                penSize.setText(size + "");
                picView.sizePicker = size;
                break;
            case R.id.add:
                size++;
                penSize.setText(size + "");
                picView.sizePicker = size;
                break;
            case R.id.recover:
                picView.flag = 7;
                picView.setTransScale((picView.firstS / picView.mCenterScale), -picView.mTransX, -picView.mTransY);
                break;
            case R.id.cx:
                picView.saveBitmap("lky.png");
                picView.cx();
                break;
        }
    }

    @Override
    public void onChoose(Line line) {

    }

    @Override
    public void onPoint(PointF pointF) {
        xzb.setText(pointF.x + "");
        yzb.setText(pointF.y + "");
    }
}
