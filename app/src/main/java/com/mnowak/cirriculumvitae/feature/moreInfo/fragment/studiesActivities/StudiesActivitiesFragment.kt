package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.mnowak.cirriculumvitae.R
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class StudiesActivitiesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        //        recyclerView.setAdapter(new StudiesActivitiesRecyclerViewAdapter(studiesActivities));
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    companion object {

        fun newInstance(): StudiesActivitiesFragment {
            return StudiesActivitiesFragment()
        }
    }
}
