package com.assistant.fronted.Tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.FacultyAffairActivity
import com.assistant.fronted.UI.Faculty.FacultyNotificationActivity
import com.assistant.fronted.UI.Faculty.FacultySettingActivity
import com.assistant.fronted.UI.Faculty.Fragments.AffairFragment
import com.assistant.fronted.UI.Faculty.Fragments.NotificationFragment
import com.assistant.fronted.UI.Faculty.Fragments.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

object FacultyNavigation {
    /**
     * 初始化导航栏
     * @param id 设置当前选中的导航栏图标
     * @param context 上下文
     * @param bottomNavigationView 导航栏id
     */
    fun initialize(id: Int, viewPager2: ViewPager2, bottomNavigationView: BottomNavigationView){
        bottomNavigationView.selectedItemId = id
        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.Tnotification -> {
                    viewPager2.setCurrentItem(0,true)
                    true
                }
                R.id.Taffair -> {
                    viewPager2.setCurrentItem(1,true)
                    true
                }
                R.id.Tsetting -> {
                    viewPager2.setCurrentItem(2,true)
                    true
                }
                else -> false
            }
        }
    }

}