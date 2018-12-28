package com.mnowak.cirriculumvitae.feature.moreInfo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MoreInfoPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> pages;

    public MoreInfoPagerAdapter(FragmentManager fm, List<Fragment> pages) {
        super(fm);
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

}
