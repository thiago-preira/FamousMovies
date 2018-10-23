package com.udacity.android.famousmovies.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.adapter.TabsAdapter;
import com.udacity.android.famousmovies.fragments.MoviesFragment;
import com.udacity.android.famousmovies.utils.DeviceUtils;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpViewPagerTabs();
    }

    private void setUpViewPagerTabs(){
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(),getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);

        int color = ContextCompat.getColor(getContext(),R.color.white);

        tabLayout.setTabTextColors(color,color);
    }
}
