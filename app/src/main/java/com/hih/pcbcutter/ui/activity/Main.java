package com.hih.pcbcutter.ui.activity;

/**
 * Created by likeye on 2020/8/13 19:05.
 **/

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.ui.adapter.MyAdapter;

import java.util.ArrayList;

public class Main extends Activity {
    private MyAdapter mAdapter;
    private ArrayList<String> list;
    private Button bt_cancel;
    private Button bt_deselectall;
    private int checkNum; // 记录选中的条目数量
    private TextView tv_show;// 用于显示选中的条目数量

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /* 实例化各个控件 */
        ListView lv = (ListView) findViewById(R.id.lv);
        Button bt_selectall = (Button) findViewById(R.id.bt_selectall);
        tv_show = (TextView) findViewById(R.id.tv);
        list = new ArrayList<>();
        // 为Adapter准备数据
        initDate();
        // 实例化自定义的MyAdapter
        mAdapter = new MyAdapter(list, this);
        // 绑定Adapter
        lv.setAdapter(mAdapter);

        // 全选按钮的回调接口
        bt_selectall.setOnClickListener(v -> {
            // 遍历list的长度，将MyAdapter中的map值全部设为true
            for (int i = 0; i < list.size(); i++) {
                MyAdapter.getIsSelected().put(i, true);
            }
            // 数量设为list的长度
            checkNum = list.size();
            // 刷新listview和TextView的显示
            dataChanged();
        });

        // 绑定listView的监听器
        lv.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
            MyAdapter.ViewHolder holder = (MyAdapter.ViewHolder) arg1.getTag();
            // 改变CheckBox的状态

            // 调整选定条目
            if (holder.cb.isChecked() == true) {
                checkNum++;
            } else {
                checkNum--;
            }
            // 用TextView显示
            tv_show.setText("已选中" + checkNum + "项");
        });
    }

    // 初始化数据
    private void initDate() {
        for (int i = 0; i < 15; i++) {
            list.add("data" + " " + i);
        }
    }
    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        tv_show.setText("已选中" + checkNum + "项");
    }

}