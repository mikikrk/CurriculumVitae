package com.mnowak.curriculumvitae.feature.moreInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mnowak.curriculumvitae.liveData.type.StringResLiveData
import com.mnowak.curriculumvitae.R
import com.mnowak.curriculumvitae.data.model.PersonalInfo
import com.mnowak.curriculumvitae.liveData.InitializedLiveData

private const val EXPERIENCE_PAGE = 0
private const val STUDIES_PAGE = 1
private const val SKILLS_PAGE = 2
private val TITLES_IDS = intArrayOf(R.string.more_info_title_experience, R.string.more_info_title_studies, R.string.more_info_title_skills)

abstract class MoreInfoViewModel : ViewModel() {

    abstract val selectedPage: LiveData<Int>
    abstract val activityTitle: StringResLiveData
    abstract val name: LiveData<String>
    abstract val position: LiveData<String>
    abstract val photoUri: LiveData<String>

    abstract fun onPageChanged(page: Int)
}

class MoreInfoViewModelImpl(

        repository: MoreInfoRepository

) : MoreInfoViewModel() {

    private val personalInfo: LiveData<PersonalInfo> by lazy {
        repository.personalInfo
    }

    override val selectedPage = InitializedLiveData(EXPERIENCE_PAGE)
    override val activityTitle = selectedPage.map { page ->
        page.let { TITLES_IDS[it] }
    }
    override val name: LiveData<String> = personalInfo.map { it.name }
    override val position: LiveData<String> = personalInfo.map { it.position }
    override val photoUri: LiveData<String> = personalInfo.map { it.photoUri }

    override fun onPageChanged(page: Int) {
        selectedPage.postValue(page)
    }
}
