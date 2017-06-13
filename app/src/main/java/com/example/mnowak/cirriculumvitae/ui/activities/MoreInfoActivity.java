package com.example.mnowak.cirriculumvitae.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.databinding.ActivityMoreInfoBinding;
import com.example.mnowak.cirriculumvitae.models.PersonalInfoViewModel;
import com.example.mnowak.cirriculumvitae.ui.adapters.MoreInfoPagerAdapter;
import com.example.mnowak.cirriculumvitae.ui.fragments.ExperienceFragment;
import com.example.mnowak.cirriculumvitae.ui.fragments.StudiesActivitiesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreInfoActivity extends AppCompatActivity {

    public static final String PERSONAL_INFO_EXTRA = "personal_info";

    private static final int EXPERIENCE_PAGE = 0;
    private static final int STUDIES_PAGE = 1;
    private static final int SKILLS_PAGE = 2;
    private static final int[] TITLES_IDS = {R.string.more_info_title_experience, R.string.more_info_title_studies, R.string.more_info_title_skills};
    private PersonalInfoViewModel personalInfoViewModel;

    @BindView(R.id.vpContent)
    ViewPager vpContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMoreInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_more_info);
        retrieveDataFromIntent();
        binding.setViewModel(personalInfoViewModel);
        ButterKnife.bind(this);
        initActionBar();
        prepareViewPager();
    }

    private void retrieveDataFromIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {
            personalInfoViewModel = (PersonalInfoViewModel) extras.getSerializable(PERSONAL_INFO_EXTRA);
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            setTitle(TITLES_IDS[EXPERIENCE_PAGE]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prepareViewPager() {
        List<Fragment> screens = getUsedPages();
        MoreInfoPagerAdapter screenPagerAdapter = new MoreInfoPagerAdapter(getSupportFragmentManager(), screens);
        vpContent.setAdapter(screenPagerAdapter);
        vpContent.addOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setTitle(TITLES_IDS[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public List<Fragment> getUsedPages() {
        List<Fragment> screens = new ArrayList<>();
        screens.add(ExperienceFragment.newInstance(personalInfoViewModel.experience));
        screens.add(StudiesActivitiesFragment.newInstance(personalInfoViewModel.activities));
        return screens;
    }
}
