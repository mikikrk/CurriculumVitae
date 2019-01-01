package com.mnowak.cirriculumvitae.liveData.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class SourceDataHandler<SourceType, ReturnType>(

        private val mediatorLiveData: MediatorLiveData<ReturnType>,
        private val onChanged: () -> Unit

) {

    private var source: LiveData<out SourceType>? = null
    var data: SourceType? = null

    fun setSource(source: LiveData<out SourceType>?) {
        if (this.source != source) {
            this.source?.let { mediatorLiveData.removeSource(it) }
            this.source = source
            if (source != null) {
                mediatorLiveData.addSource(source) {
                    data = it
                    onChanged()
                }
            }
        }
    }
}
