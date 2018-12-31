package com.mnowak.cirriculumvitae.di.viewModel

import com.mnowak.cirriculumvitae.feature.personal.PersonalInfoRepository
import com.mnowak.cirriculumvitae.feature.personal.PersonalInfoViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelsModule {

        @Provides
        @IntoMap
        @ViewModelKey(PersonalInfoViewModel::class)
        fun providePersonalInfoVM(repository: PersonalInfoRepository) = PersonalInfoViewModel(repository)
}
