package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.mnowak.cirriculumvitae.R
import kotlinx.android.synthetic.main.fragment_experience.*

class ExperienceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rvCompanies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        rvCompanies.adapter = ExperienceAdapter(experience, requireContext())
    }

    companion object {

        fun newInstance(): ExperienceFragment {
            return ExperienceFragment()
        }
    }
}
