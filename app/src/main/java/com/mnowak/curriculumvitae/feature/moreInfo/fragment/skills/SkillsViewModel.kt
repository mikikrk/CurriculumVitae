package com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.list.SkillsSetItemViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.list.SkillsSetItemViewModelImpl

abstract class SkillsViewModel : ViewModel() {

    abstract val skills: LiveData<List<SkillsSetItemViewModel>>
}

class SkillsViewModelImpl(

        repository: SkillsRepository

) : SkillsViewModel() {

    override val skills: LiveData<List<SkillsSetItemViewModel>> by lazy {
        repository.skills.map { skillsSet ->
            skillsSet.map {
                SkillsSetItemViewModelImpl(it) as SkillsSetItemViewModel
            }
        }
    }
}
