package com.mnowak.cirriculumvitae.feature.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mnowak.cirriculumvitae.data.model.PersonalInfo
import com.mnowak.cirriculumvitae.liveData.map

class PersonalInfoViewModel(

        repository: PersonalInfoRepository

) : ViewModel() {

    private val personalInfo: LiveData<PersonalInfo> by lazy {
        repository.personalInfo
    }

    val name: LiveData<String> = personalInfo.map { it?.name }
    val position: LiveData<String> = personalInfo.map { it?.position }
    val birthDate: LiveData<String> = personalInfo.map { it?.birthDate }
    val phone: LiveData<String> = personalInfo.map { it?.phone }
    val email: LiveData<String> = personalInfo.map { it?.email }
    val location: LiveData<String> = personalInfo.map { it?.location }
    val photoUri: LiveData<String> = personalInfo.map { it?.photoUri }
}
