package com.nyayozangu.labs.fuqua.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nyayozangu.labs.fuqua.R;
import com.nyayozangu.labs.fuqua.adapters.ViewPagerAdapter;
import com.nyayozangu.labs.fuqua.fragments.HistoryFragment;
import com.nyayozangu.labs.fuqua.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate items
        ViewPager homeViewPager = findViewById(R.id.homeViewPager);
        setupViewPager(homeViewPager);
        TabLayout homeTabLayout = findViewById(R.id.homeTabLayout);
        homeTabLayout.setupWithViewPager(homeViewPager);

    }

    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new HomeFragment(), getResources().getString(R.string.home_text));
        mAdapter.addFragment(new HistoryFragment(), getResources().getString(R.string.history_text));
        mViewPager.setAdapter(mAdapter);
    }
}
