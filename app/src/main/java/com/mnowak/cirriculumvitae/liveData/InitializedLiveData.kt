package com.mnowak.cirriculumvitae.liveData

import androidx.lifecycle.MutableLiveData

class InitializedLiveData<T>(

        initialValue: T

) : MutableLiveData<T>() {

    init {
        postValue(initialValue)
    }
}
