package com.assistant.fronted.Tools

import androidx.viewpager2.widget.ViewPager2
import com.assistant.fronted.R
import com.google.android.material.bottomnavigation.BottomNavigationView

object StudentNavigation {
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
                R.id.Snotification -> {
                    viewPager2.setCurrentItem(0,true)
                    true
                }
                R.id.Sarrange -> {
                    viewPager2.setCurrentItem(1,true)
                    true
                }
                R.id.Saffair -> {
                    viewPager2.setCurrentItem(2,true)
                    true
                }
                R.id.Sachievement -> {
                    viewPager2.setCurrentItem(3,true)
                    true
                }
                else -> false
            }
        }
    }
}