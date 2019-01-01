package com.mnowak.cirriculumvitae.di.view

import com.mnowak.cirriculumvitae.feature.moreInfo.MoreInfoActivity
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.experience.ExperienceFragment
import com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesFragment
import com.mnowak.cirriculumvitae.feature.personal.PersonalInfoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeModule {

    @ContributesAndroidInjector
    abstract fun contributePersonalInfoActivity(): PersonalInfoActivity

    @ContributesAndroidInjector
    abstract fun contributeMoreInfoActivity(): MoreInfoActivity

    @ContributesAndroidInjector
    abstract fun contributeExperienceFragment(): ExperienceFragment

    @ContributesAndroidInjector
    abstract fun contributeStudiesActivitiesFragment(): StudiesActivitiesFragment
}
