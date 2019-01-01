package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.mnowak.cirriculumvitae.databinding.ItemExperienceCompanyBinding
import com.mnowak.cirriculumvitae.data.model.CandidateJob
import com.mnowak.cirriculumvitae.utils.ExperienceDimensHelper

class ExperienceAdapter(

        private val companies: List<CandidateJob>, context: Context

) : RecyclerView.Adapter<ExperienceAdapter.CompanyViewHolder>() {

    private val experienceDimensHelper: ExperienceDimensHelper = ExperienceDimensHelper(context.resources)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemExperienceCompanyBinding.inflate(layoutInflater, parent, false)
        return CompanyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val candidateJob = companies[position]
        holder.bind(candidateJob)
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    inner class CompanyViewHolder (private val itemBinding: ItemExperienceCompanyBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(candidateJob: CandidateJob) {
            itemBinding.viewModel = candidateJob
            itemBinding.dimensHelper = experienceDimensHelper
            itemBinding.executePendingBindings()
        }
    }
}
