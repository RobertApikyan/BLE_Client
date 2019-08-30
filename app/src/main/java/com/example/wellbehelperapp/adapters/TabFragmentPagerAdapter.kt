package com.example.wellbehelperapp.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wellbehelperapp.fragments.BaseTabFragment

class TabFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments:List<BaseTabFragment>
): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = fragments[position].tabTitle
}