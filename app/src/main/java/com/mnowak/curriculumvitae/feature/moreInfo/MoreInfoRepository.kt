package com.mnowak.curriculumvitae.feature.moreInfo

import androidx.lifecycle.LiveData
import com.mnowak.curriculumvitae.data.CandidatesProvider
import com.mnowak.curriculumvitae.data.model.PersonalInfo
import com.mnowak.curriculumvitae.liveData.InitializedLiveData
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
