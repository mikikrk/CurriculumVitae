package com.mnowak.curriculumvitae.liveData

import androidx.lifecycle.MutableLiveData

class InitializedLiveData<T>(

        initialValue: T

) : MutableLiveData<T>() {

    init {
        value = initialValue
    }
}
