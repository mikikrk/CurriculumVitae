package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.data.CandidatesProvider
import com.mnowak.cirriculumvitae.data.model.CandidateJob
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ExperienceRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val experience: LiveData<List<CandidateJob>>
        get() = InitializedLiveData(candidatesProvider.experience)
}
