package com.example.mnowak.cirriculumvitae.models;

import java.util.List;

public class PersonalInfoViewModel extends PicturedViewModel {

    public String name;
    public String position;
    public String birthDate;
    public String phone;
    public String email;
    public String location;
    public String photoUri;

    public List<CompanyViewModel> experience;
    public List<StudiesActivityViewModel> activities;
}
