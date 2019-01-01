package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience.list.ExperienceAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_experience.*
import javax.inject.Inject

class ExperienceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter = ExperienceAdapter(this)

    private val viewModel: ExperienceViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ExperienceViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        rvCompanies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCompanies.adapter = adapter
    }

    private fun observe() {
        viewModel.experience.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object {

        fun newInstance(): ExperienceFragment {
            return ExperienceFragment()
        }
    }
}
