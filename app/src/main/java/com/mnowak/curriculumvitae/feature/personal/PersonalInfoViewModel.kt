package com.mnowak.curriculumvitae.feature.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mnowak.curriculumvitae.data.model.PersonalInfo
import com.mnowak.curriculumvitae.liveData.map

abstract class PersonalInfoViewModel: ViewModel() {

    abstract val name: LiveData<String>
    abstract val position: LiveData<String>
    abstract val birthDate: LiveData<String>
    abstract val phone: LiveData<String>
    abstract val email: LiveData<String>
    abstract val location: LiveData<String>
    abstract val photoUri: LiveData<String>
}

class PersonalInfoViewModelImpl(

        repository: PersonalInfoRepository

) : PersonalInfoViewModel() {

    private val personalInfo: LiveData<PersonalInfo> by lazy {
        repository.personalInfo
    }

    override val name: LiveData<String> = personalInfo.map { it?.name }
    override val position: LiveData<String> = personalInfo.map { it?.position }
    override val birthDate: LiveData<String> = personalInfo.map { it?.birthDate }
    override val phone: LiveData<String> = personalInfo.map { it?.phone }
    override val email: LiveData<String> = personalInfo.map { it?.email }
    override val location: LiveData<String> = personalInfo.map { it?.location }
    override val photoUri: LiveData<String> = personalInfo.map { it?.photoUri }
}
