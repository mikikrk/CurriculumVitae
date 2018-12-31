package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills;

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
import com.mnowak.cirriculumvitae.data.model.SkillsSet;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillsFragment extends Fragment {
    private static final String ARG_SKILLS_MODEL = "skillsModel";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<SkillsSet> skills;

    public static SkillsFragment newInstance(List<SkillsSet> skills) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SKILLS_MODEL, skills.toArray(new SkillsSet[]{}));
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
            if (serializableActivities instanceof SkillsSet[]) {
                skills = Arrays.asList((SkillsSet[]) serializableActivities);
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
