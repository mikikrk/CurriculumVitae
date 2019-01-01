package com.mnowak.cirriculumvitae.feature.moreInfo

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.databinding.ActivityMoreInfoBinding
import com.mnowak.cirriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience.ExperienceFragment
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills.SkillsFragment
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesFragment
import com.mnowak.cirriculumvitae.utils.onPageChanged
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_more_info.*
import javax.inject.Inject

class MoreInfoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MoreInfoViewModel by lazy {
        ViewModelProviders.of(this, factory).get(MoreInfoViewModel::class.java)
    }

    private val screens = listOf(ExperienceFragment.newInstance(),
            StudiesActivitiesFragment.newInstance(),
            SkillsFragment.newInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        initActionBar()
        prepareViewPager()
        observeTitle()
    }

    private fun bindView() {
        val binding = DataBindingUtil.setContentView<ActivityMoreInfoBinding>(this, R.layout.activity_more_info)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun prepareViewPager() {
        val screenPagerAdapter = MoreInfoPagerAdapter(supportFragmentManager, screens)
        vpContent.adapter = screenPagerAdapter
        vpContent.onPageChanged(viewModel::onPageChanged)
    }

    private fun observeTitle() {
        viewModel.activityTitle.observe(this, Observer {
            supportActionBar?.setTitle(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
