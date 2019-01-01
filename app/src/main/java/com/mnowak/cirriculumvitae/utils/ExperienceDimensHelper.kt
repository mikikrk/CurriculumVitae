package com.mnowak.cirriculumvitae.utils

import android.content.res.Resources
import androidx.databinding.BindingAdapter
import androidx.annotation.Dimension
import android.view.View
import android.view.ViewGroup

import com.mnowak.cirriculumvitae.R

class ExperienceDimensHelper(private val resources: Resources) {

    private val markerTopMargin: Int by lazy {
        resources.getDimensionPixelSize(R.dimen.chronology_marker_top_margin)
    }
    private val markerSize: Int by lazy {
        resources.getDimensionPixelSize(R.dimen.chronology_element_width)
    }
    private val trianglePointerSize: Int by lazy {
        resources.getDimensionPixelSize(R.dimen.company_to_marker_triangle_pointer_size)
    }

    val trianglePointerMarginTop: Int by lazy {
        markerTopMargin + markerSize / 2 - trianglePointerSize / 2
    }
}
