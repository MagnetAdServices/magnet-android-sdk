package com.magnetadservices.magnetadsample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.magnetadservices.magnetadsample.adapters.PagerAdapter;
import com.magnetadservices.magnetadsample.fragments.MobileBannerFragment;
import com.magnetadservices.magnetadsample.fragments.MRectFragment;
import com.magnetadservices.magnetadsample.fragments.NativeExpressFragment;
import com.magnetadservices.magnetadsample.fragments.NativeFragment;
import com.magnetadservices.magnetadsample.fragments.InterstitialFragment;
import com.magnetadservices.magnetadsample.fragments.NativeListViewFragment;
import com.magnetadservices.magnetadsample.fragments.NativeRecyclerViewFragment;
import com.magnetadservices.sdk.MagnetSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MagnetSDK.initialize(getApplicationContext());
        MagnetSDK.getSettings().setTestMode(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Magnet Sample Application");
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MobileBannerFragment(), "Banner");
        adapter.addFragment(new MRectFragment(), "MRect Banner");
        adapter.addFragment(new NativeFragment(), "Native");
        adapter.addFragment(new NativeExpressFragment(), "Native Express");
        adapter.addFragment(new InterstitialFragment(), "Interstitial");
        adapter.addFragment(new NativeListViewFragment(), "Native in ListView");
        adapter.addFragment(new NativeRecyclerViewFragment(), "Native in RecyclerView");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
    }
}
