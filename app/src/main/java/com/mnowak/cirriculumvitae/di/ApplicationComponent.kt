package com.mnowak.cirriculumvitae.di

import android.app.Application
import com.mnowak.cirriculumvitae.CirriculumVitaeApplication
import com.mnowak.cirriculumvitae.di.viewModel.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelsModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: CirriculumVitaeApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
