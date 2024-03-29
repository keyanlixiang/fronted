package com.assistant.fronted.UI

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FacultyFragmentAdapter(activity: AppCompatActivity,val fragmentList: List<Fragment>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}