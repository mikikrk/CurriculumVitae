package com.example.mnowak.cirriculumvitae.utils;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.support.annotation.Dimension;
import android.view.View;
import android.view.ViewGroup;

import com.example.mnowak.cirriculumvitae.R;

public class ExperienceDimensHelper {
    private Resources resources;
    private @Dimension int markerTopMargin;
    private @Dimension int markerSize;
    private @Dimension int trianglePointerSize;

    public @Dimension int trianglePointerMarginTop;

    public ExperienceDimensHelper(Resources resources) {
        this.resources = resources;
        readDefinedResources();
        countResources();
    }

    private void countResources() {
        trianglePointerMarginTop = markerTopMargin + markerSize / 2 - trianglePointerSize / 2;
    }

    private void readDefinedResources() {
        markerTopMargin = resources.getDimensionPixelSize(R.dimen.chronology_marker_top_margin);
        markerSize = resources.getDimensionPixelSize(R.dimen.chronology_element_width);
        trianglePointerSize = resources.getDimensionPixelSize(R.dimen.company_to_marker_triangle_pointer_size);
    }

    @BindingAdapter("android:layout_marginTop")
    public static void setMarinTop(View view, int margin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.topMargin = margin;
    }

    @BindingAdapter("android:layout_marginStart")
    public static void setMarinStart(View view, int margin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMarginStart(margin);
    }

}
