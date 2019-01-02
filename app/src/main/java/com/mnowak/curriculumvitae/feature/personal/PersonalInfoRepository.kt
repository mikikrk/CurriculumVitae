package com.mnowak.curriculumvitae.feature.personal

import androidx.lifecycle.LiveData
import com.mnowak.curriculumvitae.data.CandidatesProvider
import com.mnowak.curriculumvitae.data.model.PersonalInfo
import com.mnowak.curriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PersonalInfoRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val personalInfo: LiveData<PersonalInfo>
        get() = InitializedLiveData(candidatesProvider.personalInfo)
}
