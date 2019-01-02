package com.mnowak.curriculumvitae.feature.moreInfo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MoreInfoPagerAdapter(fm: FragmentManager, private val pages: List<Fragment>) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = pages.size

    override fun getItem(position: Int): Fragment = pages[position]
}
