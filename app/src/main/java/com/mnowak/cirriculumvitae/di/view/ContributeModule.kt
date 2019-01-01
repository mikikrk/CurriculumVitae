package com.mnowak.cirriculumvitae.di.view

import com.mnowak.cirriculumvitae.feature.personal.PersonalInfoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeModule {

    @ContributesAndroidInjector
    abstract fun contributePersonalDataActivity(): PersonalInfoActivity
}
