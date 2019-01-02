package com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience

import androidx.lifecycle.LiveData
import com.mnowak.curriculumvitae.data.CandidatesProvider
import com.mnowak.curriculumvitae.data.model.CandidateJob
import com.mnowak.curriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ExperienceRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val experience: LiveData<List<CandidateJob>>
        get() = InitializedLiveData(candidatesProvider.experience)
}
