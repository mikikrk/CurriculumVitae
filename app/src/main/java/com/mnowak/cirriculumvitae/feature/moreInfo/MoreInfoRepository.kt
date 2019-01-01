package com.mnowak.cirriculumvitae.feature.moreInfo

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.data.CandidatesProvider
import com.mnowak.cirriculumvitae.data.model.PersonalInfo
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class MoreInfoRepository @Inject constructor(

        candidatesProvider: CandidatesProvider

) {

    val personalInfo: LiveData<PersonalInfo> by lazy {
        InitializedLiveData(candidatesProvider.personalInfo)
    }
}
