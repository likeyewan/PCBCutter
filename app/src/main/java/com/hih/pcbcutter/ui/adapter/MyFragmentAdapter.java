package com.hih.pcbcutter.ui.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by likeye on 2020/8/6 10:20.
 **/
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fgList;
    private final List<String> list;
    public MyFragmentAdapter(List<Fragment> fgList, List<String> list, FragmentManager fm) {
        super(fm);
        this.fgList = fgList;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fgList.get(position);
    }

    @Override
    public int getCount() {
        return fgList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}

