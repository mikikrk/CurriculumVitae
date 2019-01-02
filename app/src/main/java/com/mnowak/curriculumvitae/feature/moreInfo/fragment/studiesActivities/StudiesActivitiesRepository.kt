package com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities

import androidx.lifecycle.LiveData
import com.mnowak.curriculumvitae.data.CandidatesProvider
import com.mnowak.curriculumvitae.data.model.StudiesActivity
import com.mnowak.curriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class StudiesActivitiesRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val studiesActivities: LiveData<List<StudiesActivity>>
        get() = InitializedLiveData(candidatesProvider.studiesActivities)
}