package com.hih.pcbcutter.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.hih.pcbcutter.R;
import com.hih.pcbcutter.pic.other.PicActivity;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.ui.activity.Main;
import com.hih.pcbcutter.ui.activity.PhotoActivity;
import com.hih.pcbcutter.ui.bean.DataSet;
import com.hih.pcbcutter.ui.bean.MachiningP;
import com.hih.pcbcutter.util.FileUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by likeye on 2020/7/24 9:21.
 **/
public class TopFragment3 extends Fragment implements ApplicationUtil.Top3Listener {
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;
    @BindView(R.id.edit_ip)
    EditText editIp;
    @BindView(R.id.edit_socket)
    EditText editSocket;
    @BindView(R.id.connect)
    Button connect;
    @BindView(R.id.aqgdz)
    EditText aqgdz;
    @BindView(R.id.aqgdy)
    EditText aqgdy;
    @BindView(R.id.qyz)
    EditText qyz;
    @BindView(R.id.qyy)
    EditText qyy;
    @BindView(R.id.qgz)
    EditText qgz;
    @BindView(R.id.qgy)
    EditText qgy;
    @BindView(R.id.vqz)
    EditText vqz;
    @BindView(R.id.vqy)
    EditText vqy;
    @BindView(R.id.pcbz)
    EditText pcbz;
    @BindView(R.id.pcby)
    EditText pcby;
    @BindView(R.id.djzj)
    EditText djzj;
    @BindView(R.id.ddjl)
    EditText ddjl;
    @BindView(R.id.xdcs)
    EditText xdcs;
    @BindView(R.id.vqsd)
    TextView vqsd;
    @BindView(R.id.kdsd)
    TextView kdsd;
    @BindView(R.id.djsm)
    CheckBox djsm;
    @BindView(R.id.kzk)
    Button kzk;
    @BindView(R.id.messd)
    Button messd;
    @BindView(R.id.ddjc)
    CheckBox ddjc;
    @BindView(R.id.qfl)
    Button qfl;
    @BindView(R.id.ggmm)
    Button ggmm;
    @BindView(R.id.smms)
    CheckBox smms;
    @BindView(R.id.xg)
    Button xg;
    @BindView(R.id.tmsm)
    CheckBox tmsm;
    @BindView(R.id.lx)
    CheckBox lx;
    @BindView(R.id.meslj)
    CheckBox meslj;
    @BindView(R.id.dxxz)
    Spinner dxxz;
    @BindView(R.id.jgf)
    EditText jgf;
    @BindView(R.id.hdwzx)
    EditText hdwzx;
    @BindView(R.id.hdwzy)
    EditText hdwzy;
    @BindView(R.id.xflw)
    EditText xflw;
    @BindView(R.id.xqlw)
    EditText xqlw;
    @BindView(R.id.cl)
    EditText cl;
    @BindView(R.id.zzxg)
    EditText zzxg;
    private ApplicationUtil appUtil;
    private final MachiningP machiningP = new MachiningP();
    private List<DataSet> list;
    private List<DataSet> mesList;
    @BindView(R.id.dm)
    Button dm;
    @BindView(R.id.qgsdz)
    TextView qgsdz;
    @BindView(R.id.qgzd)
    ImageButton qgzd;
    @BindView(R.id.qgzs)
    SeekBar qgzs;
    @BindView(R.id.qgza)
    ImageButton qgza;
    @BindView(R.id.qgsdy)
    TextView qgsdy;
    @BindView(R.id.qgyd)
    ImageButton qgyd;
    @BindView(R.id.qgys)
    SeekBar qgys;
    @BindView(R.id.qgya)
    ImageButton qgya;
    @BindView(R.id.txtj)
    Button txtj;
    @BindView(R.id.sbfw)
    Button sbfw;
    @BindView(R.id.zdgx)
    Button zdgx;
    @BindView(R.id.zmb1)
    Button zmb1;
    @BindView(R.id.zmb2)
    Button zmb2;
    @BindView(R.id.sdgx)
    Button sdgx;
    @BindView(R.id.lmb1)
    Button lmb1;
    @BindView(R.id.lm1)
    Button lm1;
    @BindView(R.id.lt1)
    Button lt1;
    @BindView(R.id.rmb1)
    Button rmb1;
    @BindView(R.id.rm1)
    Button rm1;
    @BindView(R.id.rt1)
    Button rt1;
    @BindView(R.id.lmb2)
    Button lmb2;
    @BindView(R.id.lm2)
    Button lm2;
    @BindView(R.id.lt2)
    Button lt2;
    @BindView(R.id.rmb2)
    Button rmb2;
    @BindView(R.id.rm2)
    Button rm2;
    @BindView(R.id.rt2)
    Button rt2;
    @BindView(R.id.qdqwz)
    Button qdqwz;
    @BindView(R.id.yzdqwz)
    Button yzdqwz;
    @BindView(R.id.img_z1)
    ImageView imgZ1;
    @BindView(R.id.img_y1)
    ImageView imgY1;
    private boolean isfirst = true;
    @BindView(R.id.kdz)
    ImageButton kdz;
    @BindView(R.id.kds)
    SeekBar kds;
    @BindView(R.id.kdy)
    ImageButton kdy;
    private Map<String, String> mesMap;
    private Map<String, String> machMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t2, container, false);
        ButterKnife.bind(this, view);
        imgZ1.setImageBitmap(getLoacalBitmap("/sdcard/test.png"));
        init();
        return view;
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
    private void init() {
        mesMap = new HashMap<>();
        machMap = new HashMap<>();
        // LogUtil.d("ds","map="+map.get("01"));
        djsm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("刀具寿命;1");
            } else {
                appUtil.sendData("刀具寿命;0");
            }
        });
        smms.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("寿命模式;1");
            } else {
                appUtil.sendData("寿命模式;0");
            }
        });
        ddjc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("断刀检测;1");
            } else {
                appUtil.sendData("断刀检测;0");
            }
        });
        tmsm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("条码扫描;1");
            } else {
                appUtil.sendData("条码扫描;0");
            }
        });
        lx.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("条码类型选择;1");
            } else {
                appUtil.sendData("条码类型选择;0");
            }
        });
        meslj.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("MES连接;1");
            } else {
                appUtil.sendData("MES连接;0");
            }
        });
        kdz.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                updateAddOrSubtract(v.getId());    //手指按下时触发不停的发送消息
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                stopAddOrSubtract();    //手指抬起时停止发送
            } else {
                stopAddOrSubtract();
            }
            return false;
        });
        kdy.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                updateAddOrSubtract(v.getId());    //手指按下时触发不停的发送消息
            } else {
                stopAddOrSubtract();
            }
            return false;
        });
        kds.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                kdsd.setText("" + (kds.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        qgzs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qgsdz.setText("" + (qgzs.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        qgys.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qgsdy.setText("" + (qgys.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    @Override
    public void onReceiveData(int flag, String data) {

        String[] str = data.split(";");
        if (str[0].equals("MES参数")) {
            String[][] strings = new String[str.length][];
            mesList = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                strings[i] = str[i].split(",");
            }
            for (int i = 1; i < str.length; i++) {
                mesMap.put(strings[i][0], strings[i][1]);
                DataSet dataSet = new DataSet();
                dataSet.setName(strings[i][0]);
                dataSet.setNum(strings[i][1]);
                mesList.add(dataSet);
            }
            String[] a = mesList.get(2).getNum().split("、");

            spinnerModel(a, dxxz);
            setMes();
        }
        if (str[0].equals("加工参数")) {
            String[][] strings = new String[str.length][];
            for (int i = 0; i < str.length; i++) {
                strings[i] = str[i].split(",");
                //LogUtil.d("ss","str="+strings[i].toString());
            }
            LogUtil.d("ss", "str=" + str.length);
            String[] cs = new String[str.length - 1];
            for (int i = 1; i < str.length; i++) {
                machMap.put(strings[i][0], strings[i][1]);
                cs[i - 1] = strings[i][1];
            }
            machiningP.setParameter(cs);
            setData();
        }
        if (str[0].equals("安全高度")) {
            aqgdz.setText(str[1]);
        }
    }

    private void setMes() {
        editIp.setText(mesMap.get("IP"));
        editSocket.setText(mesMap.get("端口号"));
    }

    private void setData() {
        aqgdz.setText(machiningP.getAqgd0());
        aqgdy.setText(machiningP.getAqgd1());
        qyz.setText(machiningP.getQygd0());
        qyy.setText(machiningP.getQygd1());
        qgz.setText(machiningP.getQggd0());
        qgy.setText(machiningP.getQggd1());
        vqz.setText(machiningP.getVqgd0());
        vqy.setText(machiningP.getVqgd1());
        pcbz.setText(machiningP.getPcb0());
        pcby.setText(machiningP.getPcb1());
        djzj.setText(machiningP.getDjzj());
        ddjl.setText(machiningP.getDdsyjl());
        xdcs.setText(machiningP.getXdcs());
        kdsd.setText(machiningP.getKdsdtj());
        xqlw.setText(machiningP.getXqlw());
        xflw.setText(machiningP.getXflw());
        hdwzx.setText(machiningP.getHdwzx());
        hdwzy.setText(machiningP.getHdwzy());
        cl.setText(machiningP.getCl());
        zzxg.setText(machiningP.getZzxg());
        qgsdz.setText(machiningP.getQgsdtj0());
        qgsdy.setText(machiningP.getQgsdtj1());
        kds.setProgress(Integer.parseInt(machiningP.getKdsdtj()));
        qgzs.setProgress(Integer.parseInt(machiningP.getQgsdtj0()));
        qgys.setProgress(Integer.parseInt(machiningP.getQgsdtj1()));
        if (machiningP.getDjsm().equals("1")) {
            djsm.setChecked(true);
        } else {
            djsm.setChecked(false);
        }
        if (machiningP.getDdjc().equals("1")) {
            ddjc.setChecked(true);
        } else {
            ddjc.setChecked(false);
        }
        if (machiningP.getSmms().equals("1")) {
            smms.setChecked(true);
        } else {
            smms.setChecked(false);
        }
        if (machiningP.getTmsm().equals("1")) tmsm.setChecked(true);
        else tmsm.setChecked(false);
        if (machiningP.getTmlxxz().equals("1")) lx.setChecked(true);
        else lx.setChecked(false);
        if (machiningP.getMeslj().equals("1")) meslj.setChecked(true);
        else meslj.setChecked(false);
    }

    private void getData() {
        list = new ArrayList<>();
        getText(aqgdz, "安全高度0");
        getText(aqgdy, "安全高度1");
        getText(qyz, "取样高度0");
        getText(qyy, "取样高度1");
        getText(qgz, "切割高度0");
        getText(qgy, "切割高度1");
        getText(vqz, "V切高度0");
        getText(vqy, "V切高度1");
        getText(pcbz, "PCB厚度0");
        getText(pcby, "PCB厚度1");
        getText(djzj, "刀具直径");
        getText(ddjl, "单段使用距离");
        getText(xdcs, "下刀次数");

    }

    //数组转换成固定格式的字符串
    private String getStr(List<?> list) {
        StringBuilder str = new StringBuilder("加工参数");
        String[][] s1 = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            s1[i] = list.get(i).toString().split(",");
        }
        if (s1.length > 0) {
            for (String[] strings : s1) {
                str.append(";");
                for (int j = 0; j < 2; j++) {
                    str.append(strings[j] + "");
                    if (j != 1)
                        str.append(",");
                }
            }
        }
        return str + "";
    }

    private String getS() {
        StringBuilder str = new StringBuilder("加工参数");
        Set<String> key = machMap.keySet();
        Collection<String> coll = machMap.values();
        Iterator<String> it = key.iterator();
        Iterator<String> it2 = coll.iterator();
        while (it.hasNext() && it2.hasNext()) {
            str.append(";");
            str.append(it.next() + ",");
            str.append(it2.next() + "");
        }
        return str + "";
    }

    private void getText(EditText et, String str) {
        DataSet dataSet = new DataSet();
        dataSet.setName(str);
        dataSet.setNum(et.getText().toString());
        machMap.put(str, et.getText().toString());
        list.add(dataSet);
    }

    private void spinnerModel(String[] strings, Spinner spinner) {
        //将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, strings);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    private ScheduledExecutorService scheduledExecutor;

    private void updateAddOrSubtract(int viewId) {
        final int vid = viewId;
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleWithFixedDelay(() -> {
            Message msg = new Message();
            msg.what = vid;
            handler.sendMessage(msg);
        }, 0, 100, TimeUnit.MILLISECONDS);    //每间隔100ms发送Message
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int viewId = msg.what;
            switch (viewId) {
                case R.id.kdz:
                    kds.setProgress(kds.getProgress() - 1);
                    kdsd.setText("" + kds.getProgress());
                    break;
                case R.id.kdy:
                    kds.setProgress(kds.getProgress() + 1);
                    kdsd.setText("" + kds.getProgress());
                    break;
            }
        }
    };

    private void stopAddOrSubtract() {
        if (scheduledExecutor != null) {
            scheduledExecutor.shutdownNow();
            scheduledExecutor = null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isfirst) {
            isfirst = false;
            appUtil = (ApplicationUtil) getActivity().getApplication();
            appUtil.setOnTop3Listener(this);
            appUtil.sendData("加工参数");
            appUtil.sendData("MES参数");
        }
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
      //  Intent intent = new Intent(getActivity(), CaptureActivity.class);
       // startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(getContext(), "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                editIp.setText("你扫描到的内容是：" + content);
            }
        }
    }

    @OnClick({R.id.kzk, R.id.qdqwz, R.id.yzdqwz, R.id.messd, R.id.qfl, R.id.ggmm, R.id.xg, R.id.connect, R.id.dm, R.id.txtj, R.id.sbfw, R.id.zdgx, R.id.zmb1, R.id.zmb2, R.id.sdgx, R.id.lmb1, R.id.lm1, R.id.lt1, R.id.rmb1, R.id.rm1, R.id.rt1, R.id.lmb2, R.id.lm2, R.id.lt2, R.id.rmb2, R.id.rm2, R.id.rt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kzk:
                break;
            case R.id.messd:
                break;
            case R.id.txtj:
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.dm:
                //动态权限申请
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                break;
            case R.id.qfl:
                break;
            case R.id.ggmm:
                break;
            case R.id.xg:
                getData();
                String str = getStr(list);
                String str1 = getS();
                FileUtils.writeTxtToFile(str1, "/storage/emulated/0/HIH/", "加工参数.txt");
                appUtil.sendData(str1);
                break;
            case R.id.connect:
                String address = editIp.getText().toString();
                int port = Integer.parseInt(editSocket.getText().toString());
                appUtil.setSocket(address, port);
                appUtil.init();
                break;
            case R.id.sbfw:
                break;
            case R.id.zdgx:
                break;
            case R.id.zmb1:
                Intent intent1 = new Intent(getActivity(), Main.class);
                startActivity(intent1);
                break;
            case R.id.zmb2:
                break;
            case R.id.sdgx:
                break;
            case R.id.lmb1:
                Intent intent2 = new Intent(getActivity(), PicActivity.class);
                startActivity(intent2);
                break;
            case R.id.lm1:
                break;
            case R.id.lt1:
                break;
            case R.id.rmb1:
                break;
            case R.id.rm1:
                break;
            case R.id.rt1:
                break;
            case R.id.lmb2:
                break;
            case R.id.lm2:
                break;
            case R.id.lt2:
                break;
            case R.id.rmb2:
                break;
            case R.id.rm2:
                break;
            case R.id.rt2:
                break;
            case R.id.qdqwz:
                appUtil.sendData("取当前位置");
                break;
            case R.id.yzdqwz:
                break;
        }
    }

}
