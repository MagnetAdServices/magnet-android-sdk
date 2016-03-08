package ir.magnet.sample.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.magnet.sample.MagnetPage;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    private String titles[] ;

    public ViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return MagnetPage.newInstance(position);
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}