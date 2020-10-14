package com.hih.pcbcutter.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.tcp.LogUtil;
import com.hih.pcbcutter.test.TT;
import com.hih.pcbcutter.ui.adapter.ViewPagerTopAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/7/22 9:57.
 **/
public class SetFragment extends Fragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.tab_layout_set)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_set)
    ViewPager mViewPager;
    private static boolean isFirstLoad=true;
    private ApplicationUtil appUtil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, mView);
        initTabLayout();
        return mView;
    }

    private void initTabLayout() {
        PagerAdapter adapter=new ViewPagerTopAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(this);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&isFirstLoad) {
            isFirstLoad=false;

        }
        if(isVisibleToUser){
            SharedPreferences.Editor sp=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
            sp.putInt("falg",1);
            sp.apply();
        }
        Log.d("cc1","s="+isVisibleToUser);
    }
    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("cc1","resume");
    }
    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("cc1", "start");
    }
    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d("cc1","stop");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("cc1","pause");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        TT.i=state;
    }
}
