package com.mnowak.curriculumvitae.liveData

import androidx.lifecycle.LiveData
import com.mnowak.curriculumvitae.liveData.mediator.combined.CombinedMediatorLiveData
import com.mnowak.curriculumvitae.liveData.mediator.combined.TrippleCombinedMediatorLiveData
fun <FirstSourceType, SecondSourceType, ThirdSourceType, ReturnType> combine(
        firstSourceData: LiveData<FirstSourceType>? = null,
        secondSourceData: LiveData<SecondSourceType>? = null,
        thirdSourceData: LiveData<ThirdSourceType>? = null,
        transformation: (firstSourceData: FirstSourceType?, secondSourceData: SecondSourceType?, thirdSourceData: ThirdSourceType?) -> ReturnType?) =
        TrippleCombinedMediatorLiveData(firstSourceData, secondSourceData, thirdSourceData, transformation)

fun <FirstInput, SecondInput, Output> combine(firstData: LiveData<FirstInput>?,
                                              secondData: LiveData<SecondInput>,
                                              transformation: (firstInput: FirstInput?, secondInput: SecondInput?) -> Output?) =
        CombinedMediatorLiveData(firstData, secondData, transformation)
