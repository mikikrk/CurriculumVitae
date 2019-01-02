package com.mnowak.curriculumvitae.di.viewModel

import androidx.lifecycle.ViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.MoreInfoRepository
import com.mnowak.curriculumvitae.feature.moreInfo.MoreInfoViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.MoreInfoViewModelImpl
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.ExperienceRepository
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.ExperienceViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.ExperienceViewModelImpl
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.SkillsRepository
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.SkillsViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.SkillsViewModelImpl
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesRepository
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesViewModel
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesViewModelImpl
import com.mnowak.curriculumvitae.feature.personal.PersonalInfoRepository
import com.mnowak.curriculumvitae.feature.personal.PersonalInfoViewModel
import com.mnowak.curriculumvitae.feature.personal.PersonalInfoViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelsModule {

    @Provides
    @IntoMap
    @ViewModelKey(PersonalInfoViewModel::class)
    fun providePersonalInfoVM(repository: PersonalInfoRepository): ViewModel = PersonalInfoViewModelImpl(repository)

    @Provides
    @IntoMap
    @ViewModelKey(MoreInfoViewModel::class)
    fun provideMoreInfoVM(repository: MoreInfoRepository): ViewModel = MoreInfoViewModelImpl(repository)

    @Provides
    @IntoMap
    @ViewModelKey(ExperienceViewModel::class)
    fun provideExperienceVM(repository: ExperienceRepository): ViewModel = ExperienceViewModelImpl(repository)

    @Provides
    @IntoMap
    @ViewModelKey(StudiesActivitiesViewModel::class)
    fun provideStudiesActivitiesVM(repository: StudiesActivitiesRepository): ViewModel = StudiesActivitiesViewModelImpl(repository)

    @Provides
    @IntoMap
    @ViewModelKey(SkillsViewModel::class)
    fun provideSkillsVM(repository: SkillsRepository): ViewModel = SkillsViewModelImpl(repository)
}
