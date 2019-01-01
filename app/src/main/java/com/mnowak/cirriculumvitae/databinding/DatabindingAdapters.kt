package com.mnowak.cirriculumvitae.databinding

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("android:layout_marginTop")
fun setMarinTop(view: View, margin: Int) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = margin
}

@BindingAdapter("android:layout_marginStart")
fun setMarinStart(view: View, margin: Int) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginStart = margin
}
