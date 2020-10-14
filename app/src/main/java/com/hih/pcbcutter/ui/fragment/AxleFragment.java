package com.hih.pcbcutter.ui.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.test.TT;
import com.hih.pcbcutter.ui.activity.TestActivity;
import com.hih.pcbcutter.ui.bean.Axle;
import com.hih.pcbcutter.ui.bean.PointMove;
import com.hih.pcbcutter.ui.view.SuspendButtonLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static java.lang.Thread.sleep;

public class AxleFragment extends Fragment implements ApplicationUtil.TopListener {
    @BindView(R.id.layout)
    SuspendButtonLayout suspendButtonLayout;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.zhouhao)
    Spinner zhouhao;
    @BindView(R.id.mchs)
    EditText mchs;
    @BindView(R.id.lj)
    EditText lj;
    @BindView(R.id.qssd)
    EditText qssd;
    @BindView(R.id.yxsd)
    EditText yxsd;
    @BindView(R.id.hlsd)
    EditText hlsd;
    @BindView(R.id.jsd)
    EditText jsd;
    @BindView(R.id.jiansd)
    EditText jiansd;
    @BindView(R.id.rxwz)
    EditText rxwz;
    @BindView(R.id.rxwf)
    EditText rxwf;
    @BindView(R.id.yxzt)
    TextView yxzt;
    @BindView(R.id.dqwz)
    TextView dqwz;
    @BindView(R.id.xw)
    Button xw;
    @BindView(R.id.hl)
    Button hl;
    @BindView(R.id.zhx)
    Button zhx;
    @BindView(R.id.fx)
    Button fx;
    @BindView(R.id.dq)
    Button dq;
    @BindView(R.id.shd)
    Button shd;
    @BindView(R.id.tc)
    Button tc;
    @BindView(R.id.axle_f)
    RelativeLayout axleF;
    @BindView(R.id.cx)
    Button cx;
    @BindView(R.id.cxzwz)
    Button cxzwz;
    @BindView(R.id.cxzzt)
    Button cxzzt;
    @BindView(R.id.linear)
    LinearLayout linear;
    private int flag1 = 0;
    private boolean ff = false;
    private List<String> listS = new ArrayList<>();
    private List<String> listDt = new ArrayList<>();
    private final List<Axle> axles = new ArrayList<>();
    private List<PointMove> pointMoves = new ArrayList<>();
    private ApplicationUtil appUtil;
    private AlertDialog alertDialog;
    private boolean isFirstLoad = true;
    private boolean isr = false;
    private boolean isFf = true;
    private static volatile boolean stop = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.axle_fragment, container, false);
        ButterKnife.bind(this, mView);
        init();
        return mView;
    }

    //初始化
    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        appUtil = (ApplicationUtil) Objects.requireNonNull(getActivity()).getApplication();
        appUtil.setOnTopCListener(this);
        appUtil.sendData("轴总数");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用定时器,每隔200毫秒让handler发送一个空信息
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                appUtil.sendData("轴位置;" + flag1);

            }
        }, 0,200);*/
        //appUtil.sendData("轴状态;"+flag1);
        Thread mThread = new Thread(() -> {

            while (true) {

                if (!stop && isr) {
                    isr = false;
                    if (TT.i == 1) stop = true;
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    appUtil.sendData("轴位置;" + flag1);
                    LogUtil.d("dd", "dd");
                    //appUtil.sendData("轴状态;"+flag1);
                }
            }
        });
        mThread.start();

        zhx.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // appUtil.sendData("轴移动;"+flag1+";"+axles.get(flag1).getRxwz()+";"+Integer.valueOf(yxsd.getText().toString())+";1");
                TT.i = 0;
                isr = true;
                stop = false;

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                isr = false;
                //  appUtil.sendData("轴停止;"+flag1);
                stop = true;
                // appUtil.sendData("轴位置;"+flag1);
            }

            return false;
        });

        fx.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // appUtil.sendData("轴移动;"+flag1+";"+axles.get(flag1).getRxwf()+";"+Integer.valueOf(yxsd.getText().toString())+";1");
                stop = false;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // appUtil.sendData("轴停止;"+flag1);
                stop = true;
            }
            return false;
        });
        for (int j = 0; j < axles.size(); j++) {
            listS.add(axles.get(j).getAxlenum() + "");
        }
        //悬浮按钮
        suspendButtonLayout.setOnSuspendListener(new SuspendButtonLayout.OnSuspendListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onButtonStatusChanged(int status) {
                if (status == 1) {
                    fab1.setVisibility(View.VISIBLE);
                    zhouhao.setEnabled(false);
                    zhx.setEnabled(false);
                    fx.setEnabled(false);
                    yxzt.setEnabled(false);
                    dqwz.setEnabled(false);
                    hl.setEnabled(false);
                    shd.setEnabled(false);
                    cx.setEnabled(false);
                } else {
                    fab1.setVisibility(View.GONE);
                    zhouhao.setEnabled(true);
                    zhx.setEnabled(true);
                    fx.setEnabled(true);
                    yxzt.setEnabled(true);
                    dqwz.setEnabled(true);
                    hl.setEnabled(true);
                    shd.setEnabled(true);
                    cx.setEnabled(true);
                }
            }

            @Override
            public void onChildButtonClick(int index) {
                switch (index) {
                    case 1:
                        appUtil.sendData("轴移动;3;" + axles.get(0).getRxwf() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 2:
                        appUtil.sendData("轴移动;5;" + axles.get(0).getRxwf() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 3:
                        appUtil.sendData("轴移动;0;" + axles.get(0).getRxwz() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 4:
                        appUtil.sendData("轴移动;5;" + axles.get(0).getRxwz() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 5:
                        appUtil.sendData("轴移动;3;" + axles.get(0).getRxwz() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 6:
                        appUtil.sendData("轴移动;1;" + axles.get(0).getRxwz() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 7:
                        appUtil.sendData("轴移动;0;" + axles.get(0).getRxwf() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    case 8:
                        appUtil.sendData("轴移动;1;" + axles.get(0).getRxwf() + ";" + axles.get(0).getYxsd() + ";1");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTouchUp(int index) {
                switch (index) {
                    case 1:
                        LogUtil.d("ss", "aa停止");
                        appUtil.sendData("轴停止;3");
                        break;
                    case 2:
                    case 4:
                        //appUtil.sendData("右上");
                        appUtil.sendData("轴停止;5");
                        break;
                    case 3:
                    case 7:
                        appUtil.sendData("轴停止;0");
                        break;
                    case 5:
                        appUtil.sendData("轴停止;3");
                        break;
                    case 6:
                    case 8:
                        appUtil.sendData("轴停止;1");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.xw, R.id.hl, R.id.zhx, R.id.fx, R.id.dq, R.id.shd, R.id.tc, R.id.axle_f, R.id.cx, R.id.cxzwz, R.id.cxzzt,R.id.fab1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.axle_f:
                hideKeyboard(view); //在此调用隐藏键盘的方法
                break;
            case R.id.xw:
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
                break;
            case R.id.hl:
                StringBuilder strhl = new StringBuilder("复位;");
                for (int i = 0; i < axles.size(); i++) {
                    strhl.append(axles.get(i).getAxlenum() + "");
                    if (i != axles.size() - 1) strhl.append(",");
                }
                String sHl = ("复位;" + flag1);
                appUtil.sendData(sHl + "");
                break;
            case R.id.zhx:
                String strZhx = "正移";
                break;
            case R.id.fx:
                String strfx = "负移";
                //dialogZy(strfx);
                break;
            case R.id.dq:
                appUtil.setOnTopCListener(this);
                appUtil.sendData("轴总数");
                appUtil.sendData("轴参数;0");
                break;
            case R.id.shd:
                String str1 = getStr("轴参数", axles);
                appUtil.sendData("" + str1);
                break;
            case R.id.tc:
                listDt = new ArrayList<>();
                String[] strings = new String[listS.size()];
                for (int i = 0; i < listS.size(); i++) {
                    strings[i] = "" + i;
                }
                // showStopDialog(strings);
                appUtil.sendData("轴停止;" + flag1);
                break;
            case R.id.cx:
                /*StringBuffer strcx = new StringBuffer("轴位置;");
                for (int i = 0; i < axles.size(); i++) {
                    strcx.append(axles.get(i).getAxlenum() + "");
                    if (i != axles.size() - 1) strcx.append(",");
                }
                appUtil.sendData(strcx + "");*/
                // Intent intent=new Intent(getActivity(), Main.class);
                //startActivity(intent);
                break;
            case R.id.cxzwz:
                appUtil.sendData("轴位置;" + flag1);
                break;
            case R.id.cxzzt:
                appUtil.sendData("轴状态;" + flag1);
                break;
            case R.id.fab1:
                appUtil.sendData("速度;" + "高速");
                break;
        }
    }

    private void spinnerModel(List<String> strings, Spinner spinner) {
        //将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_dropdown_item, strings);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int position, long id) {
                flag1 = position;
                if (ff) {
                    appUtil.sendData("轴参数;" + position);
                }
                ff = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    //设置轴数值
    private void setAxle(int i) {
        Axle axle = new Axle();
        axle.setAxlenum(i + 1);
        if (!mchs.getText().equals("") && mchs.getText() != null) {
            axle.setPulse(Integer.parseInt(mchs.getText().toString()));
        }
        if (!lj.getText().equals("") && lj.getText() != null) {
            axle.setScrewPitch(Integer.parseInt(lj.getText().toString()));
        }
        if (!qssd.getText().equals("") && qssd.getText() != null) {
            axle.setQssd(Integer.parseInt(qssd.getText().toString()));
        }
        if (!yxsd.getText().equals("") && yxsd.getText() != null) {
            axle.setYxsd(Integer.parseInt(yxsd.getText().toString()));
        }
        if (!hlsd.getText().equals("") && hlsd.getText() != null) {
            axle.setHlsd(Integer.parseInt(hlsd.getText().toString()));
        }
        if (!jsd.getText().equals("") && jsd.getText() != null) {
            axle.setJsd(Integer.parseInt(jsd.getText().toString()));
        }
        if (!jiansd.getText().equals("") && jiansd.getText() != null) {
            axle.setJiansd(Integer.parseInt(jiansd.getText().toString()));
        }
        if (!rxwz.getText().equals("") && rxwz.getText() != null) {
            axle.setRxwz(Integer.parseInt(rxwz.getText().toString()));
        }
        if (!rxwf.getText().equals("") && rxwf.getText() != null) {
            axle.setRxwf(Integer.parseInt(rxwf.getText().toString()));
        }
        axles.set(i, axle);
    }
    //更改轴参数
    @SuppressLint("SetTextI18n")
    private void changeAxle(int i) {
        mchs.setText(axles.get(i).getPulse() + "");
        lj.setText(axles.get(i).getScrewPitch() + "");
        qssd.setText(axles.get(i).getQssd() + "");
        yxsd.setText(axles.get(i).getYxsd() + "");
        hlsd.setText(axles.get(i).getHlsd() + "");
        jsd.setText(axles.get(i).getJsd() + "");
        jiansd.setText(axles.get(i).getJiansd() + "");
        rxwz.setText(axles.get(i).getRxwz() + "");
        rxwf.setText(axles.get(i).getRxwf() + "");
        // yxzt.setText(axles.get(i).getYxzt() + "");
        // dqwz.setText(axles.get(i).getDqwz() + "");
    }

    @Override
    public void onReceiveData(int flag, String data) {
        if (flag == 100) {
            String[] a = data.split(";");
            if (a[0].equals("轴总数")) {
                listS = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(a[1]); i++) {
                    listS.add(i + "");
                }
                spinnerModel(listS, zhouhao);
                for (int i = 0; i < listS.size(); i++) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    appUtil.sendData("轴参数;" + i);
                }
            }
            if (a[0].equals("轴参数")) {
                List<String> list = new ArrayList<>();
                String[] b = a[1].split(",");
                list.add(flag1 + "");
                for (String s : b) {
                    list.add(s);
                }
                if (axles.size() <= listS.size()) {
                    Axle axle = new Axle();
                    axle.set(list);
                    axles.add(axle);
                } else {
                    Axle axle = new Axle();
                    axle.set(list);
                    // axles.set(flag1,axle);
                }

                changeAxle(flag1);
            }
            if (a[0].equals("轴状态")) {
                yxzt.setText(a[1]);

            }
            if (a[0].equals("轴位置")) {
                isr = true;
                dqwz.setText(a[1]);
                LogUtil.d("ss", "ss");
            }
        }
    }
    /**
     * 隐藏键盘
     */
    private void hideKeyboard(View v) {
        ((InputMethodManager) Objects.requireNonNull(getActivity().getSystemService(INPUT_METHOD_SERVICE)))
                .hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //停止轴号窗口
    private void showStopDialog(String[] items) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("单停轴号：");
        alertBuilder.setMultiChoiceItems(items, null, (dialogInterface, i, isChecked) -> {
            if (isChecked) {
                listDt.add((i + 1) + "");
            } else {
                listDt.remove((i + 1) + "");
            }
        });
        alertBuilder.setPositiveButton("确定", (dialogInterface, i) -> {
            StringBuilder stringBuffer = new StringBuilder("单停;");
            for (int j = 0; j < listDt.size(); j++) {
                stringBuffer.append(listDt.get(j) + "");
                if (j != listDt.size() - 1) stringBuffer.append(",");
            }
            appUtil.sendData("" + stringBuffer);
            alertDialog.dismiss();
        });
        alertBuilder.setNegativeButton("取消", (dialogInterface, i) -> alertDialog.dismiss());
        alertDialog = alertBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    //数组转换成固定格式的字符串
    private String getStr(String name, List<?> list) {
        StringBuilder str = new StringBuilder(name);
        String[][] s1 = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            s1[i] = list.get(i).toString().split(",");
        }
        if (s1.length > 0) {
            for (int i = 0; i < s1[0].length; i++) {
                str.append(";");
                for (int j = 0; j < list.size(); j++) {
                    str.append(s1[j][i] + "");
                    if (j != list.size() - 1)
                        str.append(",");
                }
            }
        }
        return str + "";
    }

    private int jxd = 1;

    //点动操作对话框
    private void dialogZy(String fx) {
        pointMoves = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ddcz, null, false);
        builder.setView(view);
        final Dialog dialog = builder.create();
        final Button qx = view.findViewById(R.id.reconnect_qx);
        final Button qd = view.findViewById(R.id.reconnect_qd);
        // final Button pre = view.findViewById(R.id.ddcz_pre);
        final Button next = view.findViewById(R.id.ddcz_next);
        EditText axleNum = view.findViewById(R.id.zhouhao);
        EditText wzEt = view.findViewById(R.id.wz);
        EditText sdEt = view.findViewById(R.id.sd);
        RadioGroup jx = view.findViewById(R.id.rg_jdxd);
        RadioButton jd = view.findViewById(R.id.rb_jd);
        RadioButton xd = view.findViewById(R.id.rb_xd);
        next.setOnClickListener(v -> {
            if (!axleNum.getText().toString().equals("") && !wzEt.getText().toString().equals("") && !sdEt.getText().toString().equals("")) {
                PointMove pointMove = new PointMove();
                pointMove.setAxleNum(Integer.parseInt(axleNum.getText().toString()));
                // if(!wzEt.getText().toString().equals(""))
                pointMove.setWz(Integer.parseInt(wzEt.getText().toString()));
                // if(!sdEt.getText().toString().equals(""))
                pointMove.setSd(Integer.parseInt(sdEt.getText().toString()));
                pointMove.setJxd(jxd);
                pointMoves.add(pointMove);
                axleNum.setText("");
                wzEt.setText("");
                sdEt.setText("");
            } else {
                Toast.makeText(getContext(), "不能为空!", Toast.LENGTH_SHORT).show();
            }
        });
        //RadioGroup zf=view.findViewById(R.id.rg_ydfx);
        jx.setOnCheckedChangeListener((group, checkedId) -> {
            if (jd.getId() == checkedId) {
                jxd = 1;
            }
            if (xd.getId() == checkedId) {
                jxd = 0;
            }
        });
        qd.setOnClickListener(v -> {
            if (!axleNum.getText().toString().equals("") && !wzEt.getText().toString().equals("") && !sdEt.getText().toString().equals("")) {
                PointMove pointMove = new PointMove();
                pointMove.setAxleNum(Integer.parseInt(axleNum.getText().toString()));
                pointMove.setWz(Integer.parseInt(wzEt.getText().toString()));
                pointMove.setSd(Integer.parseInt(sdEt.getText().toString()));
                pointMove.setJxd(jxd);
                pointMoves.add(pointMove);
                String str = getStr(fx, pointMoves);
                appUtil.sendData(str + "");
                dialog.dismiss();
            } else {
                Toast.makeText(getContext(), "不能为空!", Toast.LENGTH_SHORT).show();
            }
            // StringBuffer str = new StringBuffer(fx);
           /* for (int i = 0; i < 4; i++) {
                str.append(";");
                for (int j = 0; j < pointMoves.size(); j++) {
                    if (i == 0) {
                        str.append(pointMoves.get(j).getAxleNum() + "");
                    } else if (i == 1) {
                        str.append(pointMoves.get(j).getWz() + "");
                    } else if (i == 2) {
                        str.append(pointMoves.get(j).getSd() + "");
                    } else if (i == 3) {
                        str.append(pointMoves.get(j).getJxd() + "");
                    }
                    if (j != pointMoves.size() - 1) str.append(",");
                }
            }*/
        });
        qx.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = dialog.getWindow();
        WindowManager m = Objects.requireNonNull(getActivity()).getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = Objects.requireNonNull(dialogWindow).getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.32); // 高度设置为屏幕的0.6，根据实际情况调整
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(p);
    }

    //字符串转数组
    private void tranStr(String s) {
        String[] s1 = s.split(";");
        String[][] s2 = new String[s1.length][];
        List<String> strings = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();
        String name = s1[0];
        for (int i = 0; i < s1.length; i++) {
            s2[i] = s1[i].split(",");
        }
        for (int j = 0; j < s2[1].length; j++) {
            strings = new ArrayList<>();
            for (int i = 1; i < s1.length; i++) {
                strings.add(s2[i][j]);
            }
            lists.add(strings);
        }
        LogUtil.d("ss", "轴参数=" + lists.get(0).toString());
        if (name.equals("轴参数")) {
            List<Axle> list = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                Axle axle = new Axle();
                axle.set(lists.get(i));
                list.add(axle);
            }
            LogUtil.d("ss", "轴参数=" + list.get(0).getAxlenum());
        }
    }

    private class SendWzTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            while (true) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                appUtil.sendData("发送消息");
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.d("ss", "a=" + isVisibleToUser);
    }
}
