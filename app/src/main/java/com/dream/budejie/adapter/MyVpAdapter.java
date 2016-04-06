package com.dream.budejie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jerry on 2015/12/19.
 */
public class MyVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public MyVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {

        return mList.get(position);
    }

    @Override
    public int getCount() {

        return mList.size();
    }
}
