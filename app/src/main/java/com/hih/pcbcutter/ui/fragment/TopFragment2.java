package com.hih.pcbcutter.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.ui.adapter.InSignalAdapter;
import com.hih.pcbcutter.ui.adapter.SignalAdapter;
import com.hih.pcbcutter.ui.bean.Signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likeye on 2020/7/24 9:20.
 **/
public class TopFragment2 extends Fragment implements ApplicationUtil.IOListener {

    @BindView(R.id.input)
    Button input;
    @BindView(R.id.output)
    Button output;
    @BindView(R.id.in_list)
    RecyclerView inList;
    @BindView(R.id.out_list)
    RecyclerView outList;
    private AlertDialog alertDialog; //输出信号多选框
    private final List<Signal> signals = new ArrayList<>();
    private final List<Signal> signalsIn = new ArrayList<>();
    private ApplicationUtil appUtil;
    private ListView lv;
    private SignalAdapter outAdapter;
    private InSignalAdapter inAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top2, container, false);
        ButterKnife.bind(this, view);
        appUtil = (ApplicationUtil) getActivity().getApplication();
        appUtil.setOnIOListener(this);

        return view;

    }

    private void initOut(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        outList.setLayoutManager(layoutManager);
        outAdapter = new SignalAdapter(signals, getActivity());
        outAdapter.notifyDataSetChanged();
        outList.setAdapter(outAdapter);
    }
    private void initIn(View view) {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(view.getContext());
        inList.setLayoutManager(layoutManager1);
        inAdapter = new InSignalAdapter(signalsIn, getActivity());
        inAdapter.notifyDataSetChanged();
        inList.setAdapter(inAdapter);
    }
    public void showInputAlertDialog(String[] items, boolean[] booleans) {
        int a = 0;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("输入信号：");
        /**
         *第一个参数:弹出框的消息集合，一般为字符串集合
         * 第二个参数：默认被选中的，布尔类数组
         * 第三个参数：勾选事件监听
         */
        if (a == 1) {

        }
        alertBuilder.setMultiChoiceItems(items, booleans, (dialogInterface, i, isChecked) -> {
            if (isChecked) {
                // signalsIn.get(i).setChoose(true);
            } else {
                //  signalsIn.get(i).setChoose(false);
            }
        });
        alertBuilder.setPositiveButton("确定", (dialogInterface, i) -> {
            Gson gson = new Gson();
            String str = gson.toJson(signalsIn);
//                StringBuffer stringBuffer=new StringBuffer(""+signalsIn.get(0).getName()+";");
            //               stringBuffer.append(signalsIn.get(0).getChoose()+"");
            //              appUtil.sendData(""+stringBuffer);
            // alertDialog3.dismiss();
        });

       /* alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog3.dismiss();
            }
        });
*/
        //输入信号多选框
        AlertDialog alertDialog3 = alertBuilder.create();
        alertDialog3.setCanceledOnTouchOutside(false);
        alertDialog3.show();
    }
    public void showOutAlertDialog(String[] items, boolean[] booleans) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("输出信号");
        /**
         *第一个参数:弹出框的消息集合，一般为字符串集合
         * 第二个参数：默认被选中的，布尔类数组
         * 第三个参数：勾选事件监听
         */
        alertBuilder.setMultiChoiceItems(items, booleans, (dialogInterface, i, isChecked) -> {
            if (i == 5) {
                // lv.setItemChecked(chooseFlag, true);
            }
            if (isChecked) {
                appUtil.sendData(items[i] + ";1");
//                    signals.get(i).setChoose(true);
            } else {
                appUtil.sendData(items[i] + ";0");
//                   signals.get(i).setChoose(false);
            }
        });

        alertBuilder.setPositiveButton("确定", (dialogInterface, i) -> {
            Gson gson = new Gson();
            String str = gson.toJson(signals);
            appUtil.sendData("" + str);
            alertDialog.dismiss();
        });

        alertDialog = alertBuilder.create();
        new Thread(() -> lv.setItemChecked(2, true)).start();
        alertDialog.setCanceledOnTouchOutside(false);
        lv = alertDialog.getListView();
        alertDialog.show();
    }

    @Override
    public void onReceiveData(int flag, String data) {
        if (flag == 100) {
            String[] a = data.split(";");
            LogUtil.d("ss", "a=" + a[0]);
            if (a[0].equals("输入标识")) {
                String[] b = a[1].split(",");
                for (String s : b) {
                    Signal signal = new Signal();
                    signal.setChoose(false);
                    signal.setName(s);
                    signalsIn.add(signal);
                }
                initIn(getView());
                /// showInputAlertDialog(b,inB);
                for (String s : b) {
                    Signal signal = new Signal();
                    signal.setChoose(false);
                    signal.setName(s);
                }
                LogUtil.d("ss", "s=" + Arrays.toString(b));
            }
            if (a[0].equals("输出标识")) {
                String[] b = a[1].split(",");
                for (String s : b) {
                    Signal signal = new Signal();
                    signal.setChoose(false);
                    signal.setName(s);
                    signals.add(signal);
                }
                initOut(getView());
                // showOutAlertDialog(b,inB);
                LogUtil.d("ss", "s=" + Arrays.toString(b));
            }
            //获取输出信号标识
            for(int i=0;i<signals.size();i++){
                if(a[0].equals(signals.get(i).getName())){
                    Signal signal = new Signal();
                    signal.setName(a[0]);
                    if(Integer.parseInt(a[1])==1){
                        signal.setChoose(true);
                    }else {
                        signal.setChoose(false);
                    }
                    SignalAdapter.list.set(i,signal);
                    outAdapter.notifyDataSetChanged();
                    break;
                }
            }
            //获取输入信号标识
            for(int i=0;i<signalsIn.size();i++){
                if(a[0].equals(signalsIn.get(i).getName())){
                    Signal signal = new Signal();
                    signal.setName(a[0]);
                    if(Integer.parseInt(a[1])==1){
                        signal.setChoose(true);
                    }else {
                        signal.setChoose(false);
                    }
                    InSignalAdapter.list.set(i,signal);
                    inAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }
    @OnClick({R.id.input, R.id.output})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.input:
                appUtil.sendData("输入标识");

                //  showInputAlertDialog(strIn,inB);
                break;
            case R.id.output:
                appUtil.sendData("输出标识");
                //  showOutAlertDialog(str, outB);
                break;
        }
    }
}
