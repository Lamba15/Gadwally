package com.lamba.gadwally.Tables;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lamba.gadwally.R;

/**
  Created by Lamba on 2/17/2018.
 */

public class Weekly extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weeklytable);

        viewPager = findViewById(R.id.viewpager_in_table);
        tabLayout = findViewById(R.id.tablelayout);



        WeekPager adabter = new WeekPager(getSupportFragmentManager());
        adabter.Addfragment(new Saturdayfrag(), "SAT");
        adabter.Addfragment(new Sundayfrag(), "SUN");
        adabter.Addfragment(new Mondayfrag(), "MON");
        adabter.Addfragment(new Tuesdayfrag(), "TUE");
        adabter.Addfragment(new Wednsedayfrag(), "WED");
        adabter.Addfragment(new Thursdayfrag(), "THU");
        adabter.Addfragment(new Fridayfrag(), "FRI");


        viewPager.setAdapter(adabter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
