package com.mnowak.cirriculumvitae.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}
