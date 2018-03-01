package com.lamba.gadwally;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lamba.gadwally.DailyTable.DailyTable;
import com.lamba.gadwally.WeeklyTables.Weekly;

public class Main_Interface extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager pager;
    ViewPageAdabter myadabter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        pager = findViewById(R.id.viewpager);
        myadabter = new ViewPageAdabter(getApplicationContext());
        pager.setAdapter(myadabter);

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/bauhaus93.ttf");
        TextView tv_gadwally = findViewById(R.id.toolbar_title_gadwally);
        tv_gadwally.setTypeface(face);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_weekly_table) {
            startActivity(new Intent(getApplicationContext(), Weekly.class));
        } else if (id == R.id.nav_daily_table) {
            startActivity(new Intent(getApplicationContext(), DailyTable.class));
            Toast.makeText(this, "My Daily Table", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_help_feedback) {
            Toast.makeText(this, "Help & Feedback", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_save_contant) {
            Toast.makeText(this, "Save Contact", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_log_out) {
          startActivity(new Intent(Main_Interface.this , MainActivity.class));

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
