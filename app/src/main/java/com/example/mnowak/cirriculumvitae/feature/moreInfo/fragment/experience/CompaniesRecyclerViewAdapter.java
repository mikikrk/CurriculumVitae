package com.example.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mnowak.cirriculumvitae.databinding.ItemExperienceCompanyBinding;
import com.example.mnowak.cirriculumvitae.model.CompanyViewModel;
import com.example.mnowak.cirriculumvitae.utils.ExperienceDimensHelper;

import java.util.List;

public class CompaniesRecyclerViewAdapter extends RecyclerView.Adapter<CompaniesRecyclerViewAdapter.CompanyViewHolder> {

    private List<CompanyViewModel> companies;
    private ExperienceDimensHelper experienceDimensHelper;

    CompaniesRecyclerViewAdapter(List<CompanyViewModel> companies, Context context) {
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
        CompanyViewModel companyViewModel = companies.get(position);
        holder.bind(companyViewModel);
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

        public void bind(CompanyViewModel companyViewModel) {
            itemBinding.setViewModel(companyViewModel);
            itemBinding.setDimensHelper(experienceDimensHelper);
            itemBinding.executePendingBindings();
        }
    }
}
