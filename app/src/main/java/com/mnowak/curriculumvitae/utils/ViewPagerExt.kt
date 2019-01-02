package com.mnowak.curriculumvitae.utils

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageChanged(pageChanged: (pageNb: Int) -> Unit) {
    addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            pageChanged(position)
        }
    })
}
