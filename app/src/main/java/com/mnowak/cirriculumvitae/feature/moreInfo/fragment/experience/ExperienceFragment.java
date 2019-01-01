package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.data.model.CandidateJob;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExperienceFragment extends Fragment {
    private static final String ARG_EXPERIENCE_MODEL = "experience_model";

    private List<CandidateJob> experience;

    @BindView(R.id.rvCompanies)
    RecyclerView rvCompanies;

    public static ExperienceFragment newInstance() {
        return new ExperienceFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_experience, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setupRecyclerView();
    }

    private void readArguments() {
        if (getArguments() != null) {
            Serializable serializableExperience = getArguments().getSerializable(ARG_EXPERIENCE_MODEL);
            if (serializableExperience instanceof CandidateJob[]) {
                experience = Arrays.asList((CandidateJob[]) serializableExperience);
            }
        }
    }

    private void setupRecyclerView() {
        rvCompanies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCompanies.setAdapter(new CompaniesRecyclerViewAdapter(experience, requireContext()));
    }
}
