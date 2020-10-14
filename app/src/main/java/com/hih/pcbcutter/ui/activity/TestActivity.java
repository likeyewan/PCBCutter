package com.hih.pcbcutter.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.ui.bean.DataSet;
import com.hih.pcbcutter.ui.bean.SystemNum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likeye on 2020/8/4 17:16.
 **/
public class TestActivity extends AppCompatActivity implements ApplicationUtil.TestListener {
    @BindView(R.id.py_x1)
    EditText pyX1;
    @BindView(R.id.py_x2)
    EditText pyX2;
    @BindView(R.id.xjfw_x)
    EditText xjfwX;
    @BindView(R.id.bdz_x)
    EditText bdzX;
    @BindView(R.id.py_y1)
    EditText pyY1;
    @BindView(R.id.py_y2)
    EditText pyY2;
    @BindView(R.id.xjfw_y)
    EditText xjfwY;
    @BindView(R.id.bdz_y)
    EditText bdzY;
    @BindView(R.id.zk)
    Button zk;
    @BindView(R.id.ded)
    Button ded;
    @BindView(R.id.txbd)
    Button txbd;
    @BindView(R.id.xg)
    Button xg;
    @BindView(R.id.jzfw)
    EditText jzfw;
    @BindView(R.id.ylsd)
    EditText ylsd;
    @BindView(R.id.mkys)
    EditText mkys;
    @BindView(R.id.xztj)
    EditText xztj;
    @BindView(R.id.jgys)
    EditText jgys;
    @BindView(R.id.ptj)
    EditText ptj;
    @BindView(R.id.jjssj)
    EditText jjssj;
    @BindView(R.id.ptc)
    EditText ptc;
    @BindView(R.id.sdws)
    EditText sdws;
    @BindView(R.id.sdds)
    EditText sdds;
    @BindView(R.id.sdzs)
    EditText sdzs;
    @BindView(R.id.sdgs)
    EditText sdgs;
    @BindView(R.id.xlh)
    EditText xlh;
    @BindView(R.id.jc)
    Button jc;
    @BindView(R.id.aqm)
    CheckBox aqm;
    @BindView(R.id.sjjz)
    CheckBox sjjz;
    @BindView(R.id.ljxz)
    CheckBox ljxz;
    @BindView(R.id.dlx)
    CheckBox dlx;
    @BindView(R.id.xdjs)
    CheckBox xdjs;
    @BindView(R.id.azx)
    EditText azx;
    @BindView(R.id.azy)
    EditText azy;
    @BindView(R.id.ydbcx)
    EditText ydbcx;
    @BindView(R.id.ydbcy)
    EditText ydbcy;
    @BindView(R.id.ydzds)
    EditText ydzds;
    @BindView(R.id.qgzds)
    EditText qgzds;
    @BindView(R.id.ddjd)
    EditText ddjd;
    @BindView(R.id.bdr)
    EditText bdr;

    private boolean isFirst = true;
    private ApplicationUtil appUtil;
    SystemNum systemNum = new SystemNum();
    private List<DataSet> list;
    private Map<String, String> sysMap;
    @BindView(R.id.rb_chinese)
    RadioButton rbChinese;
    @BindView(R.id.rb_english)
    RadioButton rbEnglish;
    @BindView(R.id.rg_language)
    RadioGroup rgLanguage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t1);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        appUtil = (ApplicationUtil) this.getApplication();
        appUtil.setOnTestListener(this);
        appUtil.sendData("系统参数");
        String sta = getResources().getConfiguration().locale.getLanguage();
        if(sta.equals("zh"))rbChinese.setChecked(true);
        else rbEnglish.setChecked(true);
        rgLanguage.setOnCheckedChangeListener((rg, checkedId) -> {
            if(!isFirst) {
                isFirst=false;
                // TODO Auto-generated method stub
                if (checkedId == rbChinese.getId()) {
                    //String sta = "en";
                    shiftLanguage("en");
                } else if (checkedId == rbEnglish.getId()) {
                   // String sta = getResources().getConfiguration().locale.getLanguage();
                    shiftLanguage("zh");
                }
            }
        });


    }

    private void setCheckbox() {
        //安全门
        aqm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("安全门;1");
            } else {
                appUtil.sendData("安全门;0");
            }
        });
        //视觉校正
        sjjz.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("视觉校正;1");
            } else {
                appUtil.sendData("视觉校正;0");
            }
        });
        //路径修正
        ljxz.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("路径修正;1");
            } else {
                appUtil.sendData("路径修正;0");
            }
        });
        //多类型
        dlx.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("多类型;1");
            } else {
                appUtil.sendData("多类型;0");
            }
        });
        //下刀减速
        xdjs.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                appUtil.sendData("下刀减速;1");
            } else {
                appUtil.sendData("下刀减速;0");
            }
        });
    }

    @Override
    public void onReceiveData(int flag, String data) {
        String[] str = data.split(";");
        if (str[0].equals("系统参数")) {
            String[][] strings = new String[str.length][];
            sysMap = new HashMap<>();
            for (int i = 0; i < str.length; i++) {
                strings[i] = str[i].split(",");
            }
            String[] cs = new String[str.length - 1];
            for (int i = 1; i < str.length; i++) {
                cs[i - 1] = strings[i][1];
                sysMap.put(strings[i][0], strings[i][1]);
            }
            //systemNum.setNum(cs);
            setData();
            isFirst = false;
            setCheckbox();
        }
    }

    private void setText(TextView text,String str){
        text.setText(sysMap.get(str));
    }
    //将数据填入文本框
    private void setData() {
        pyX1.setText(sysMap.get("偏移Xa"));
        pyY1.setText(sysMap.get("偏移Ya"));
        setText(pyX2,"偏移Xb");
        setText(pyY2, "偏移Yb");
        setText(xjfwX, "范围X");
        setText(xjfwY, "范围Y");
        setText(bdzX, "标定X");
        setText(bdzY, "标定Y");
        setText(mkys, "模板延时");
        setText(jgys, "加工延时");
        setText(jzfw, "校正范围");
        setText(jjssj, "加减速");
        setText(ylsd, "预览速度");
        setText(xztj, "闲置停机");
        setText(ptj, "平台进");
        setText(ptc, "平台出");
        setText(xlh, "系列号");
        setText(sdws, "手动微速");
        setText(sdds, "手动低速");
        setText(sdzs, "手动中速");
        setText(sdgs, "手动高速");

        //  pyX1.setText(systemNum.getPyxa());
      /*  pyY1.setText(systemNum.getPyya());
        pyX2.setText(systemNum.getPyxb());
        pyY2.setText(systemNum.getPyyb());
        xjfwX.setText(systemNum.getFwx());
        xjfwY.setText(systemNum.getFwy());
        bdzX.setText(systemNum.getBdx());
        bdzY.setText(systemNum.getBdy());

        jzfw.setText(systemNum.getJzfw());
        ylsd.setText(systemNum.getYlsd());
        mkys.setText(systemNum.getMbys());
        xztj.setText(systemNum.getXztj());
        jgys.setText(systemNum.getJgys());
        ptj.setText(systemNum.getPtj());
        jjssj.setText(systemNum.getJjs());
        ptc.setText(systemNum.getPtc());
        sdws.setText(systemNum.getSdws());
        sdds.setText(systemNum.getSdds());
        sdzs.setText(systemNum.getSdzs());
        sdgs.setText(systemNum.getSdgs());
        xlh.setText(systemNum.getXlh());
        azx.setText(systemNum.getAzx());
        azy.setText(systemNum.getAzy());
        ddjd.setText(systemNum.getDdjd());
        bdr.setText(systemNum.getBdr());
        ydzds.setText(systemNum.getYdzds());
        qgzds.setText(systemNum.getQgzds());
        ydbcx.setText(systemNum.getYdbcx());
        ydbcy.setText(systemNum.getYdbcy());
        if (systemNum.getAqm().equals("1")) aqm.setChecked(true);
        else aqm.setChecked(false);
        if (systemNum.getSjjz().equals("1")) sjjz.setChecked(true);
        else sjjz.setChecked(false);
        if (systemNum.getLjxz().equals("1")) ljxz.setChecked(true);
        else ljxz.setChecked(false);
        if (systemNum.getDlx().equals("1")) dlx.setChecked(true);
        else dlx.setChecked(false);
        */

    }

    //获取文本框数据
    private void getData() {
        list = new ArrayList<>();
        getText(pyX1, "偏移Xa");
        getText(pyY1, "偏移Ya");
        getText(pyX2, "偏移Xb");
        getText(pyY2, "偏移Yb");
        getText(xjfwX, "范围X");
        getText(xjfwY, "范围Y");
        getText(bdzX, "标定X");
        getText(bdzY, "标定Y");
        getText(mkys, "模板延时");
        getText(jgys, "加工延时");
        getText(jzfw, "校正范围");
        getText(jjssj, "加减速");
        getText(ylsd, "预览速度");
        getText(xztj, "闲置停机");
        getText(ptj, "平台进");
        getText(ptc, "平台出");
        getText(xlh, "系列号");
        getText(sdws, "手动微速");
        getText(sdds, "手动低速");
        getText(sdzs, "手动中速");
        getText(sdgs, "手动高速");
        getText(azx, "A中X");
        getText(azy, "A中Y");
        getText(ddjd, "对刀角度");
        getText(bdr, "标定R");
        getText(ydzds, "移动最大速");
        getText(qgzds, "切割最大速");
        getText(ydbcx, "圆刀补偿X");
        getText(ydbcy, "圆刀补偿Y");
    }

    private void getText(EditText et, String str) {
        sysMap.put(str, et.getText().toString());
        DataSet dataSet = new DataSet();
        dataSet.setName(str);
        dataSet.setNum(et.getText().toString());
        list.add(dataSet);
    }

    @OnClick({R.id.zk, R.id.ded, R.id.txbd, R.id.xg, R.id.jc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zk:
                break;
            case R.id.ded:
                break;
            case R.id.txbd:
                break;
            case R.id.xg:
//                getData();
//                String s = getStr("系统参数", list);
                appUtil.sendData("系统参数");
                break;
            case R.id.jc:
                break;
        }
    }

    //数组转换成固定格式的字符串
    private String getStr(String name, List<?> list) {
        StringBuilder str = new StringBuilder(name);
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

    private void shiftLanguage(String sta) {
        if (sta.equals("zh")) {
            //转换为英文
            Resources resources = this.getResources();// 获得res资源对象
            Configuration config = resources.getConfiguration();// 获得设置对象
            DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale.US; // 英文
            resources.updateConfiguration(config, dm);
            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            startActivity(intent);
            //TestActivity.this.recreate();
        } else {
            //转换为中文
            Resources resources = this.getResources();// 获得res资源对象
            Configuration config = resources.getConfiguration();// 获得设置对象
            DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale.SIMPLIFIED_CHINESE; // 英文
            resources.updateConfiguration(config, dm);
            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            startActivity(intent);
            //TestActivity.this.recreate();
        }
    }
}
