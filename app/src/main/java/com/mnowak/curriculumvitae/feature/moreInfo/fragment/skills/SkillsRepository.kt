package com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills

import androidx.lifecycle.LiveData
import com.mnowak.curriculumvitae.data.CandidatesProvider
import com.mnowak.curriculumvitae.data.model.SkillsSet
import com.mnowak.curriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SkillsRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val skills: LiveData<List<SkillsSet>>
        get() = InitializedLiveData(candidatesProvider.skills)
}
