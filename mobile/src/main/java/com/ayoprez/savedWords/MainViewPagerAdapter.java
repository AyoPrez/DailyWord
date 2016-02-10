package com.ayoprez.savedWords;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 1/08/15.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter{

    private final ArrayList<Fragment> mFragmentList;
    private final ArrayList<String> mFragmentTitleList;

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<String> fragmentTitles, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.mFragmentTitleList = fragmentTitles;
        this.mFragmentList = fragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mFragmentTitleList.size();
    }
}