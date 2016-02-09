package ir.magnet.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import ir.magnet.sample.ui.SlidingTabLayout;

public class ContainerActivity extends ActionBarActivity {

    ViewPager pager;
    private String titles[] = new String[]{"Mobile Banner", "MRect", "Interstitial", "Rewarded Video"};
    private Toolbar toolbar;

    SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));

        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
    }
}
