package com.hih.pcbcutter.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.test.TT;
import com.hih.pcbcutter.ui.adapter.MyFragmentAdapter;
import com.hih.pcbcutter.ui.fragment.RunFragment;
import com.hih.pcbcutter.ui.fragment.SetFragment;
import com.shoulashou.demo.kt.RecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ApplicationUtil.CListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private final List<Fragment> fgList = new ArrayList<>();
    private final List<String> list = new ArrayList<>();
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.reconn)
    Button reconn;
    @BindView(R.id.linear)
    LinearLayout linear;
    private ApplicationUtil appUtil;
    @BindView(R.id.pro)
    ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        //初始化
        appUtil = (ApplicationUtil) MainActivity.this.getApplication();
        appUtil.setOnCListener(this);
    }

    private void init() {
        //添加碎片
        fgList.add(new RunFragment());
        fgList.add(new SetFragment());
        fgList.add(new RecordFragment());
       // linear.setVisibility(View.GONE);
        //添加标题文字
        list.add(getString(R.string.fragment1));
        list.add(getString(R.string.fragment2));
        list.add(getString(R.string.fragment_3));
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fgList, list, getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentAdapter);
        mViewPager.setOffscreenPageLimit(2);//设置ViewPage缓存界面数
        mViewPager.setOnPageChangeListener(this);
        mTabLayout.setupWithViewPager(mViewPager);
        //初始图标
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_pause_on);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_pause);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_pause);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中图标
                if (tab.getPosition() == 0) tab.setIcon(R.drawable.ic_pause_on);
                else if (tab.getPosition() == 1) tab.setIcon(R.drawable.ic_pause_on);
                else if (tab.getPosition() == 2) tab.setIcon(R.drawable.ic_pause_on);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中图标
                if (tab.getPosition() == 0) tab.setIcon(R.drawable.ic_pause);
                else if (tab.getPosition() == 1) tab.setIcon(R.drawable.ic_pause);
                else if (tab.getPosition() == 2) tab.setIcon(R.drawable.ic_pause);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onConnectionSucceeded() {
        //linear.setVisibility(View.GONE);
        appUtil.sendData("输入标识");
        appUtil.sendData("输出标识");
        appUtil.sendData("轴总数");
        appUtil.sendData("轴参数;0");
    }

    @Override
    public void onReceiveData(int flag, String data) {
        linear.setVisibility(View.GONE);
    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onBrokenPipe() {
        linear.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDisconnect() {

    }

    @OnClick(R.id.reconn)
    public void onViewClicked() {
        appUtil.init();
       // tv.setVisibility(View.GONE);
       // reconn.setVisibility(View.GONE);
       // pro.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
       // LogUtil.d("as","state="+state);
        TT.i=state;
    }
}
