package com.mnowak.curriculumvitae
import com.mnowak.curriculumvitae.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CurriculumVitaeApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerApplicationComponent.builder()
                .application(this)
                .build()
        component.inject(this)
        return component
    }
}
