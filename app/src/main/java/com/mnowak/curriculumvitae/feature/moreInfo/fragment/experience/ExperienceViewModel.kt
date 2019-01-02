package com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.list.ExperienceItemViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.list.ExperienceItemViewModelImpl
import com.mnowak.curriculumvitae.liveData.map

abstract class ExperienceViewModel : ViewModel() {

    abstract val experience: LiveData<List<ExperienceItemViewModel>>
}

class ExperienceViewModelImpl(

        repository: ExperienceRepository

) : ExperienceViewModel() {

    override val experience: LiveData<List<ExperienceItemViewModel>> by lazy {
        repository.experience.map { candidateJobs ->
            candidateJobs?.map {
                ExperienceItemViewModelImpl(it) as ExperienceItemViewModel
            }
        }
    }
}
