package com.example.mnowak.cirriculumvitae.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.models.StudiesActivityViewModel;
import com.example.mnowak.cirriculumvitae.ui.adapters.StudiesActivitiesRecyclerViewAdapter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudiesActivitiesFragment extends Fragment {
    private static final String ARG_ACTIVITIES_MODEL = "activitiesModel";
    @BindView(R.id.rvActivities)
    RecyclerView rvActivities;

    private List<StudiesActivityViewModel> studiesActivities;

    public static StudiesActivitiesFragment newInstance(List<StudiesActivityViewModel> studiesActivities) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ACTIVITIES_MODEL, studiesActivities.toArray(new StudiesActivityViewModel[]{}));
        StudiesActivitiesFragment fragment = new StudiesActivitiesFragment();
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
            Serializable serializableActivities = getArguments().getSerializable(ARG_ACTIVITIES_MODEL);
            if (serializableActivities != null && serializableActivities instanceof StudiesActivityViewModel[]) {
                studiesActivities = Arrays.asList((StudiesActivityViewModel[]) serializableActivities);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_studies_activities, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rvActivities.setAdapter(new StudiesActivitiesRecyclerViewAdapter(studiesActivities));
        rvActivities.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
