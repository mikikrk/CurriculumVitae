package com.mnowak.curriculumvitae.liveData.mediator

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class MediatorWrapper<ReturnType> : MutableLiveData<ReturnType>() {

    protected val mediatorLiveData = MediatorLiveData<ReturnType>()

    override fun setValue(value: ReturnType?) {
        super.setValue(value)
        mediatorLiveData.value = value
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in ReturnType>) {
        mediatorLiveData.observe(owner, observer)
    }

    override fun hasObservers(): Boolean {
        return mediatorLiveData.hasObservers()
    }

    override fun removeObservers(owner: LifecycleOwner) {
        mediatorLiveData.removeObservers(owner)
    }

    override fun observeForever(observer: Observer<in ReturnType>) {
        mediatorLiveData.observeForever(observer)
    }

    override fun removeObserver(observer: Observer<in ReturnType>) {
        mediatorLiveData.removeObserver(observer)
    }

    override fun getValue(): ReturnType? {
        return mediatorLiveData.value
    }

    override fun hasActiveObservers(): Boolean {
        return mediatorLiveData.hasActiveObservers()
    }

    override fun postValue(value: ReturnType?) {
        super.postValue(value)
        mediatorLiveData.postValue(value)
    }
}
