package com.mnowak.cirriculumvitae.feature.personal

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.databinding.ActivityPersonalDataBinding
import com.mnowak.cirriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.cirriculumvitae.feature.moreInfo.MoreInfoActivity
import com.mnowak.cirriculumvitae.utils.defaultStart
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_personal_data.*
import javax.inject.Inject

class PersonalInfoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelsFactory: ViewModelFactory

    private val viewModel: PersonalInfoViewModel by lazy {
        ViewModelProviders.of(this, viewModelsFactory).get(PersonalInfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupEvents()
    }

    private fun bindView() {
        val binding = DataBindingUtil.setContentView<ActivityPersonalDataBinding>(this, R.layout.activity_personal_data)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
    }

    private fun setupEvents() {
        fabMoreInfo.setOnClickListener {
            defaultStart<MoreInfoActivity>()
        }
    }
}
