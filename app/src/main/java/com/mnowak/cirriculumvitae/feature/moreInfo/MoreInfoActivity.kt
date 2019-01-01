package com.mnowak.cirriculumvitae.feature.moreInfo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.databinding.ActivityMoreInfoBinding
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience.ExperienceFragment
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills.SkillsFragment
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesFragment
import kotlinx.android.synthetic.main.activity_more_info.*

class MoreInfoActivity : AppCompatActivity() {

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setTitle(TITLES_IDS[position])
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

    private val usedPages = listOf(ExperienceFragment.newInstance(),
            StudiesActivitiesFragment.newInstance(),
            SkillsFragment.newInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMoreInfoBinding>(this, R.layout.activity_more_info)
        initActionBar()
        prepareViewPager()
    }

    private fun initActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            setTitle(TITLES_IDS[EXPERIENCE_PAGE])
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    private fun prepareViewPager() {
        val screens = usedPages
        val screenPagerAdapter = MoreInfoPagerAdapter(supportFragmentManager, screens)
        vpContent.adapter = screenPagerAdapter
        vpContent.addOnPageChangeListener(onPageChangeListener)
    }

    companion object {

        private val EXPERIENCE_PAGE = 0
        private val STUDIES_PAGE = 1
        private val SKILLS_PAGE = 2
        private val TITLES_IDS = intArrayOf(R.string.more_info_title_experience, R.string.more_info_title_studies, R.string.more_info_title_skills)
    }
}
