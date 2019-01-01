package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities;

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
import com.mnowak.cirriculumvitae.data.model.StudiesActivity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudiesActivitiesFragment extends Fragment {
    private static final String ARG_ACTIVITIES_MODEL = "activitiesModel";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<StudiesActivity> studiesActivities;

    public static StudiesActivitiesFragment newInstance() {
        return new StudiesActivitiesFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments();
    }

    private void readArguments() {
        if (getArguments() != null) {
            Serializable serializableActivities = getArguments().getSerializable(ARG_ACTIVITIES_MODEL);
            if (serializableActivities instanceof StudiesActivity[]) {
                studiesActivities = Arrays.asList((StudiesActivity[]) serializableActivities);
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
        recyclerView.setAdapter(new StudiesActivitiesRecyclerViewAdapter(studiesActivities));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
