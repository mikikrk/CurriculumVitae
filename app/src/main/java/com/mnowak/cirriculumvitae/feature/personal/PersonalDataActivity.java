package com.mnowak.cirriculumvitae.feature.personal;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.data.model.Candidate;
import com.mnowak.cirriculumvitae.databinding.ActivityPersonalDataBinding;
import com.mnowak.cirriculumvitae.feature.moreInfo.MoreInfoActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDataActivity extends AppCompatActivity {

    private Candidate candidate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersonalDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_data);

        Gson gson = new GsonBuilder().create();
        InputStream rawStream = getResources().openRawResource(R.raw.personal_info);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(rawStream));
        candidate = gson.fromJson(bufferedReader, Candidate.class);
        binding.setViewModel(candidate);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fabMoreInfo)
    public void onMoreInfoClicked() {
        Intent intent = new Intent(this, MoreInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(MoreInfoActivity.PERSONAL_INFO_EXTRA, candidate);
        startActivity(intent);
    }

}
