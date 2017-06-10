package com.example.mnowak.cirriculumvitae;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class PersonalDataViewModel extends ViewModel {

    public ObservableField<String> name;
    public MutableLiveData<String> position;
    public MutableLiveData<String> bithDate;
    public MutableLiveData<String> email;
    public MutableLiveData<String> location;

    public MutableLiveData<String> photoUrl;


}
