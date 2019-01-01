package com.mnowak.cirriculumvitae.liveData.mediator.combined

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.liveData.mediator.MediatorWrapper
import com.mnowak.cirriculumvitae.liveData.mediator.SourceDataHandler

open class CombinedMediatorLiveData<FirstSourceType, SecondSourceType, ReturnType>(

        firstSourceData: LiveData<FirstSourceType>? = null,
        secondSourceData: LiveData<SecondSourceType>? = null,
        private val onChangedObserver: ((firstSourceData: FirstSourceType?, secondSourceType: SecondSourceType?) -> ReturnType?)?

) : MediatorWrapper<ReturnType>() {

    protected var firstSourceDataHandler = SourceDataHandler<FirstSourceType, ReturnType>(mediatorLiveData, ::onChanged)
    protected var secondSourceHandler = SourceDataHandler<SecondSourceType, ReturnType>(mediatorLiveData, ::onChanged)

    init {
        firstSourceData?.let { setFirstSource(it) }
        secondSourceData?.let { setSecondSource(it) }
    }

    fun setFirstSource(source: LiveData<FirstSourceType>?) {
        firstSourceDataHandler.setSource(source)
    }

    fun setSecondSource(source: LiveData<SecondSourceType>?) {
        secondSourceHandler.setSource(source)
    }

    protected open fun onChanged() {
        val newValue = onChangedObserver?.invoke(firstSourceDataHandler.data, secondSourceHandler.data)
        postValue(newValue)
    }
}
