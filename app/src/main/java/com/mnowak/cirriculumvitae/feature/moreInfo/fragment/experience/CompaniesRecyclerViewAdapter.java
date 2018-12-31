package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mnowak.cirriculumvitae.databinding.ItemExperienceCompanyBinding;
import com.mnowak.cirriculumvitae.data.model.CandidateJob;
import com.mnowak.cirriculumvitae.utils.ExperienceDimensHelper;

import java.util.List;

public class CompaniesRecyclerViewAdapter extends RecyclerView.Adapter<CompaniesRecyclerViewAdapter.CompanyViewHolder> {

    private List<CandidateJob> companies;
    private ExperienceDimensHelper experienceDimensHelper;

    CompaniesRecyclerViewAdapter(List<CandidateJob> companies, Context context) {
        this.companies = companies;
        experienceDimensHelper = new ExperienceDimensHelper(context.getResources());
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemExperienceCompanyBinding itemBinding = ItemExperienceCompanyBinding.inflate(layoutInflater, parent, false);
        return new CompanyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        CandidateJob candidateJob = companies.get(position);
        holder.bind(candidateJob);
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {
        private final ItemExperienceCompanyBinding itemBinding;

        CompanyViewHolder(ItemExperienceCompanyBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(CandidateJob candidateJob) {
            itemBinding.setViewModel(candidateJob);
            itemBinding.setDimensHelper(experienceDimensHelper);
            itemBinding.executePendingBindings();
        }
    }
}
