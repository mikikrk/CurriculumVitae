package com.mnowak.cirriculumvitae.liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mnowak.cirriculumvitae.liveData.mediator.combined.CombinedMediatorLiveData
import com.mnowak.cirriculumvitae.liveData.mediator.combined.TrippleCombinedMediatorLiveData

fun <T, R> LiveData<T>.map(transformation: (T?) -> R?): LiveData<R> =
        Transformations.map(this, transformation)

fun <T, R> LiveData<T>.onChange(transformation: (T?) -> R?): LiveData<R> =
        Transformations.map(this, transformation)

fun <T, R> LiveData<T>.switchMap(transformation: (T?) -> LiveData<R>): LiveData<R> =
        Transformations.switchMap(this, transformation)

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
