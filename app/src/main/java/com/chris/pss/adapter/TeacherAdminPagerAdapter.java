package com.chris.pss.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chris.pss.fragment.TeacherAdminMajorListFragment;
import com.chris.pss.fragment.TeacherAdminProjectCheckListFragment;

/**
 * Created by zht on 2017/5/30.
 */

public class TeacherAdminPagerAdapter extends FragmentPagerAdapter {

    private String[] titleList = {"选题时间", "课题审核"};
    private Fragment[] mFragmentList = new Fragment[2];

    public TeacherAdminPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList[position] == null) {
            if (position == 0) {
                mFragmentList[position] = TeacherAdminMajorListFragment.newInstance();
            } else if (position == 1) {
                mFragmentList[position] = TeacherAdminProjectCheckListFragment.newInstance();
            }
        }
        return mFragmentList[position];
    }

    @Override
    public int getCount() {
        return titleList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }
}
