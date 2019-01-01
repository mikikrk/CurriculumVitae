package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mnowak.cirriculumvitae.data.model.CandidateJob
import com.mnowak.cirriculumvitae.liveData.InitializedLiveData

abstract class ExperienceItemViewModel : ViewModel() {

    abstract val id: LiveData<Int>
    abstract val name: LiveData<String>
    abstract val employmentTime: LiveData<String>
    abstract val position: LiveData<String>
    abstract val description: LiveData<String>
    abstract val iconUri: LiveData<String>
}

class ExperienceItemViewModelImpl(

        private val candidateJob: CandidateJob

) : ExperienceItemViewModel() {

    override val id = InitializedLiveData(candidateJob.id)
    override val name = InitializedLiveData(candidateJob.name)
    override val employmentTime = InitializedLiveData(candidateJob.employmentTime)
    override val position = InitializedLiveData(candidateJob.position)
    override val description = InitializedLiveData(candidateJob.description)
    override val iconUri = InitializedLiveData(candidateJob.iconUri)

    override fun equals(other: Any?): Boolean =
            if (other is ExperienceItemViewModelImpl) {
                candidateJob == other.candidateJob
            } else {
                super.equals(other)
            }

    override fun hashCode(): Int {
        return candidateJob.hashCode()
    }
}
