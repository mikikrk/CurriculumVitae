package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.data.CandidatesProvider
import com.mnowak.cirriculumvitae.data.model.StudiesActivity
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class StudiesActivitiesRepository @Inject constructor(

        private val candidatesProvider: CandidatesProvider

) {

    val studiesActivities: LiveData<List<StudiesActivity>>
        get() = InitializedLiveData(candidatesProvider.studiesActivities)
}