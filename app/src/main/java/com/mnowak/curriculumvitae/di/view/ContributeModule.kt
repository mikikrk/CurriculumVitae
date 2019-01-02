package com.mnowak.curriculumvitae.di.view

import com.mnowak.curriculumvitae.feature.moreInfo.MoreInfoActivity
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.ExperienceFragment
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.skills.SkillsFragment
import com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.StudiesActivitiesFragment
import com.mnowak.curriculumvitae.feature.personal.PersonalInfoActivity
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

    @ContributesAndroidInjector
    abstract fun contributeSkillsFragment(): SkillsFragment
}
