package com.mnowak.curriculumvitae.utils

import android.content.res.Resources
import androidx.annotation.Dimension

import com.mnowak.curriculumvitae.R

@Dimension
fun getExperiencePointerMarginTop(resources: Resources): Int {
    val markerTopMargin = resources.getDimensionPixelSize(R.dimen.chronology_marker_top_margin)
    val markerSize = resources.getDimensionPixelSize(R.dimen.chronology_element_width)
    val trianglePointerSize = resources.getDimensionPixelSize(R.dimen.company_to_marker_triangle_pointer_size)
    return markerTopMargin + markerSize / 2 - trianglePointerSize / 2
}
