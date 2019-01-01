package com.mnowak.cirriculumvitae.liveData.mediator.combined

import androidx.lifecycle.LiveData
import com.mnowak.cirriculumvitae.liveData.mediator.MediatorWrapper
import com.mnowak.cirriculumvitae.liveData.mediator.SourceDataHandler

class TrippleCombinedMediatorLiveData<FirstSourceType, SecondSourceType, ThirdSourceType, ReturnType> (

        firstSourceData: LiveData<FirstSourceType>? = null,
        secondSourceData: LiveData<SecondSourceType>? = null,
        thirdSourceData: LiveData<ThirdSourceType>? = null,
        private val onChangedObserver: (firstSourceData: FirstSourceType?, secondSourceData: SecondSourceType?, thirdSourceData: ThirdSourceType?) -> ReturnType?

) : MediatorWrapper<ReturnType>() {

    private var firstSourceDataHandler
            = SourceDataHandler<FirstSourceType, ReturnType>(mediatorLiveData, this::onChanged)
    private var secondSourceHandler
            = SourceDataHandler<SecondSourceType, ReturnType>(mediatorLiveData, this::onChanged)
    private var thirdSourceHandler
            = SourceDataHandler<ThirdSourceType, ReturnType>(mediatorLiveData, this::onChanged)

    init {
        firstSourceData?.let { setFirstSource(it) }
        secondSourceData?.let { setSecondSource(it) }
        thirdSourceData?.let { setThirdSource(it) }
    }

    fun setFirstSource(source: LiveData<FirstSourceType>) {
        firstSourceDataHandler.setSource(source)
    }

    fun setSecondSource(source: LiveData<SecondSourceType>) {
        secondSourceHandler.setSource(source)
    }

    fun setThirdSource(source: LiveData<ThirdSourceType>) {
        thirdSourceHandler.setSource(source)
    }

    private fun onChanged() {
        val newValue = onChangedObserver.invoke(firstSourceDataHandler.data, secondSourceHandler.data, thirdSourceHandler.data)
        postValue(newValue)
    }
}
