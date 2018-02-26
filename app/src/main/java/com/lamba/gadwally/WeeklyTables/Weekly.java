package com.lamba.gadwally.WeeklyTables;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.lamba.gadwally.R;

/**
 * Created by Lamba on 2/17/2018.
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
        Toolbar  toolbar = findViewById(R.id.weekly_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/bauhaus93.ttf");
        TextView tv_weekly = findViewById(R.id.toolbar_title_weekly);
        tv_weekly.setTypeface(face);


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
