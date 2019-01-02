package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills.list.SkillsAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class SkillsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter = SkillsAdapter(this)

    private val viewModel: SkillsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SkillsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun observe() {
        viewModel.skills.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object {

        fun newInstance(): SkillsFragment {
            return SkillsFragment()
        }
    }
}
