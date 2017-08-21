package com.magnetadservices.magnetadsample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.magnetadservices.magnetadsample.adapters.PagerAdapter;
import com.magnetadservices.magnetadsample.fragments.Tab1Fragment;
import com.magnetadservices.magnetadsample.fragments.Tab2Fragment;
import com.magnetadservices.magnetadsample.fragments.Tab3Fragment;
import com.magnetadservices.magnetadsample.fragments.Tab4Fragment;
import com.magnetadservices.magnetadsample.fragments.Tab5Fragment;
import com.magnetadservices.magnetadsample.fragments.Tab6Fragment;
import com.magnetadservices.sdk.MagnetSDK;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MagnetSDK.initialize(getApplicationContext());
        MagnetSDK.getSettings().setTestMode(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Magnet Sample Ad");
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Banner");
        adapter.addFragment(new Tab2Fragment(), "MRect Banner");
        adapter.addFragment(new Tab3Fragment(), "Native");
        adapter.addFragment(new Tab4Fragment(), "Interstitial");
        adapter.addFragment(new Tab5Fragment(), "Native in ListView");
        adapter.addFragment(new Tab6Fragment(), "Native in RecyclerView");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = LeakCanaryTest.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
