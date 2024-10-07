package com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mnowak.curriculumvitae.R
import com.mnowak.curriculumvitae.di.viewModel.ViewModelFactory
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.list.StudiesActivitiesAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StudiesActivitiesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var recyclerView: RecyclerView

    private val viewModel: StudiesActivitiesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(StudiesActivitiesViewModel::class.java)
    }
    private val adapter = StudiesActivitiesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recyclerView =
            inflater.inflate(R.layout.fragment_recycler_view, container, false) as RecyclerView
        return recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        observe()
    }

    private fun observe() {
        viewModel.studiesActivities.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    companion object {

        fun newInstance(): StudiesActivitiesFragment {
            return StudiesActivitiesFragment()
        }
    }
}
