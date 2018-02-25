package com.lamba.gadwally.WeeklyTables;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Whelllava on 2/16/2018.
 */

public class WeekPager extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentlist = new ArrayList<>();
    private ArrayList<String> fragementstitles = new ArrayList<>();

    public WeekPager(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragementstitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragementstitles.get(position);
    }


    public void Addfragment(Fragment fragments, String titles) {
        fragmentlist.add(fragments);
        fragementstitles.add(titles);
    }


}
