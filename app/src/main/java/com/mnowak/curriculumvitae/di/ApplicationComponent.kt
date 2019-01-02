package com.mnowak.curriculumvitae.di

import android.app.Application
import com.mnowak.curriculumvitae.CurriculumVitaeApplication
import com.mnowak.curriculumvitae.di.data.DataModule
import com.mnowak.curriculumvitae.di.view.ContributeModule
import com.mnowak.curriculumvitae.di.viewModel.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    DataModule::class,
    ContributeModule::class,
    ViewModelsModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: CurriculumVitaeApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
