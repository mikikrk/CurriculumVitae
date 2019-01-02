package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.data.CandidatesProvider
import com.mnowak.cirriculumvitae.data.model.SkillsSet
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SkillsRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val skills: LiveData<List<SkillsSet>>
        get() = InitializedLiveData(candidatesProvider.skills)
}
