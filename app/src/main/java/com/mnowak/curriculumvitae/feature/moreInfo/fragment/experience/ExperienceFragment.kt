package com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mnowak.curriculumvitae.R
import com.mnowak.curriculumvitae.databinding.FragmentExperienceBinding
import com.mnowak.curriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.list.ExperienceAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ExperienceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: FragmentExperienceBinding

    private val adapter = ExperienceAdapter(this)

    private val viewModel: ExperienceViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ExperienceViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentExperienceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        binding.rvCompanies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvCompanies.adapter = adapter
    }

    private fun observe() {
        viewModel.experience.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    companion object {

        fun newInstance(): ExperienceFragment {
            return ExperienceFragment()
        }
    }
}
