package com.example.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.model.SkillsViewModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillsFragment extends Fragment {
    private static final String ARG_SKILLS_MODEL = "skillsModel";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<SkillsViewModel> skills;

    public static SkillsFragment newInstance(List<SkillsViewModel> skills) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SKILLS_MODEL, skills.toArray(new SkillsViewModel[]{}));
        SkillsFragment fragment = new SkillsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments();
    }

    private void readArguments() {
        if (getArguments() != null) {
            Serializable serializableActivities = getArguments().getSerializable(ARG_SKILLS_MODEL);
            if (serializableActivities instanceof SkillsViewModel[]) {
                skills = Arrays.asList((SkillsViewModel[]) serializableActivities);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setAdapter(new SkillsRecyclerViewAdapter(skills));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}