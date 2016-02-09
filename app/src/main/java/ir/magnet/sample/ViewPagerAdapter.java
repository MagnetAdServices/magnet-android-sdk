package ir.magnet.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String titles[] ;

    public ViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MagnetPage.newInstance(position);
            case 1:
                return MagnetPage.newInstance(position);
            case 2:
                return MagnetPage.newInstance(position);
            case 3:
                return MagnetPage.newInstance(position);
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}