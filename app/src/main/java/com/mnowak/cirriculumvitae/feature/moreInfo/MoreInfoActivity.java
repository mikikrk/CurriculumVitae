package com.mnowak.cirriculumvitae.feature.moreInfo;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.data.model.Candidate;
import com.mnowak.cirriculumvitae.databinding.ActivityMoreInfoBinding;
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience.ExperienceFragment;
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills.SkillsFragment;
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesFragment;
import com.mnowak.cirriculumvitae.feature.personal.PersonalInfoViewModel;

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
    private Candidate candidate;

    @BindView(R.id.vpContent)
    ViewPager vpContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMoreInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_more_info);
        binding.setViewModel(candidate);
        ButterKnife.bind(this);
        initActionBar();
        prepareViewPager();
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
        screens.add(ExperienceFragment.newInstance(candidate.getExperience()));
        screens.add(StudiesActivitiesFragment.newInstance(candidate.getActivities()));
        screens.add(SkillsFragment.newInstance(candidate.getSkills()));
        return screens;
    }
}
