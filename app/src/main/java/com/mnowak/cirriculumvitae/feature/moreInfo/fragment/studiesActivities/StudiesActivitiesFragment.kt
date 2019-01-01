package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities.list.StudiesActivitiesAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class StudiesActivitiesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: StudiesActivitiesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(StudiesActivitiesViewModel::class.java)
    }
    private val adapter = StudiesActivitiesAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        observe()
    }

    private fun observe() {
        viewModel.studiesActivities.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object {

        fun newInstance(): StudiesActivitiesFragment {
            return StudiesActivitiesFragment()
        }
    }
}
