package com.example.mnowak.cirriculumvitae.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.databinding.ActivityPersonalDataBinding;
import com.example.mnowak.cirriculumvitae.models.PersonalInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersonalDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_data);

        Gson gson = new GsonBuilder().create();
        InputStream rawStream = getResources().openRawResource(R.raw.personal_info);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(rawStream));
        PersonalInfo personalInfo = gson.fromJson(bufferedReader, PersonalInfo.class);
        binding.setViewModel(personalInfo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fabMoreInfo)
    public void onMoreInfoClicked() {
    }
}
