package com.mnowak.curriculumvitae.feature.personal

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mnowak.curriculumvitae.R
import com.mnowak.curriculumvitae.databinding.ActivityPersonalDataBinding
import com.mnowak.curriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.curriculumvitae.feature.moreInfo.MoreInfoActivity
import com.mnowak.curriculumvitae.utils.defaultStart
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PersonalInfoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelsFactory: ViewModelFactory
    lateinit var binding: ActivityPersonalDataBinding

    private val viewModel: PersonalInfoViewModel by lazy {
        ViewModelProviders.of(this, viewModelsFactory).get(PersonalInfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupEvents()
    }

    private fun bindView() {
        binding = DataBindingUtil.setContentView<ActivityPersonalDataBinding>(this, R.layout.activity_personal_data)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
    }

    private fun setupEvents() {
        binding.fabMoreInfo.setOnClickListener {
            defaultStart<MoreInfoActivity>()
        }
    }
}
