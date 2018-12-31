package com.mnowak.cirriculumvitae.feature.personal

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.data.CandidatesProvider
import com.mnowak.cirriculumvitae.data.model.PersonalInfo
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData
import dagger.Reusable

@Reusable
class PersonalInfoRepository(

        candidatesProvider: CandidatesProvider

) {

    val personalInfo: LiveData<PersonalInfo> by lazy {
        InitializedLiveData(candidatesProvider.personalInfo)
    }
}
