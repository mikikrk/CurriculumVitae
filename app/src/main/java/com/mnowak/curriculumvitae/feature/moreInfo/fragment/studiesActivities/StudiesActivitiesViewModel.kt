package com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.list.StudiesActivitiesItemViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.list.StudiesActivitiesItemViewModelImpl

abstract class StudiesActivitiesViewModel : ViewModel() {

    abstract val studiesActivities: LiveData<List<StudiesActivitiesItemViewModel>>
}

class StudiesActivitiesViewModelImpl(

        repository: StudiesActivitiesRepository

) : StudiesActivitiesViewModel() {

    override val studiesActivities: LiveData<List<StudiesActivitiesItemViewModel>> by lazy {
        repository.studiesActivities.map { studiesActivities ->
            studiesActivities.map {
                StudiesActivitiesItemViewModelImpl(it) as StudiesActivitiesItemViewModel
            }
        }
    }
}
