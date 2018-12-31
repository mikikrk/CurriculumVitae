package com.mnowak.cirriculumvitae.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified A : Activity> Context.start(configIntent: Intent.() -> Unit = {}) {
    startActivity(Intent(this, A::class.java).apply(configIntent))
}

inline fun <reified A : Activity> Context.defaultStart() {
    start<A> {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
}
