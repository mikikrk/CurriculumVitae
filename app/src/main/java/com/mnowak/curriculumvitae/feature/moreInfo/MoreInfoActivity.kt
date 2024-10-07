package com.mnowak.curriculumvitae.feature.moreInfo

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mnowak.curriculumvitae.R
import com.mnowak.curriculumvitae.databinding.ActivityMoreInfoBinding
import com.mnowak.curriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.ExperienceFragment
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.SkillsFragment
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesFragment
import com.mnowak.curriculumvitae.utils.onPageChanged
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MoreInfoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var binding: ActivityMoreInfoBinding

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
        binding = DataBindingUtil.setContentView<ActivityMoreInfoBinding>(this, R.layout.activity_more_info)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun prepareViewPager() {
        val screenPagerAdapter = MoreInfoPagerAdapter(supportFragmentManager, screens)
        binding.vpContent.adapter = screenPagerAdapter
        binding.vpContent.onPageChanged(viewModel::onPageChanged)
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
