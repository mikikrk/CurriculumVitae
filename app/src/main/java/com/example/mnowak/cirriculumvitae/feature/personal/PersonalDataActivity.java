package com.example.mnowak.cirriculumvitae.feature.personal;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.databinding.ActivityPersonalDataBinding;
import com.example.mnowak.cirriculumvitae.feature.moreInfo.MoreInfoActivity;
import com.example.mnowak.cirriculumvitae.model.PersonalInfoViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDataActivity extends AppCompatActivity {

    private PersonalInfoViewModel personalInfoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersonalDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_data);

        Gson gson = new GsonBuilder().create();
        InputStream rawStream = getResources().openRawResource(R.raw.personal_info);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(rawStream));
        personalInfoViewModel = gson.fromJson(bufferedReader, PersonalInfoViewModel.class);
        binding.setViewModel(personalInfoViewModel);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fabMoreInfo)
    public void onMoreInfoClicked() {
        Intent intent = new Intent(this, MoreInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(MoreInfoActivity.PERSONAL_INFO_EXTRA, personalInfoViewModel);
        startActivity(intent);
    }

}
