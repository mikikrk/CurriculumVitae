package com.mnowak.cirriculumvitae.liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T, R> LiveData<T>.map(transformation: (T?) -> R?): LiveData<R> =
    Transformations.map(this, transformation)
