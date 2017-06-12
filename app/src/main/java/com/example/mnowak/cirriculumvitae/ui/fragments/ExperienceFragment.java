package com.example.mnowak.cirriculumvitae.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.databinding.FragmentExperienceBinding;
import com.example.mnowak.cirriculumvitae.models.ExperienceViewModel;
import com.example.mnowak.cirriculumvitae.ui.adapters.CompaniesRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExperienceFragment extends Fragment {
    private static final String ARG_EXPERIENCE_MODEL = "experience_model";

    private FragmentExperienceBinding binding;
    private ExperienceViewModel experienceModel;

    @BindView(R.id.rvCompanies)
    RecyclerView rvCompanies;

    public static ExperienceFragment newInstance(ExperienceViewModel experienceViewModel) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPERIENCE_MODEL, experienceViewModel);
        ExperienceFragment fragment = new ExperienceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            experienceModel = (ExperienceViewModel) getArguments().getSerializable(ARG_EXPERIENCE_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experience, container, false);
        binding.setViewModel(experienceModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        rvCompanies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCompanies.setAdapter(new CompaniesRecyclerViewAdapter(experienceModel.companies, getContext()));
    }
}
