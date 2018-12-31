package com.mnowak.cirriculumvitae.feature.personal

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.data.CandidatesProvider
import com.mnowak.cirriculumvitae.data.model.PersonalInfo
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PersonalInfoRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val personalInfo: LiveData<PersonalInfo>
        get() = InitializedLiveData(candidatesProvider.personalInfo)
}
