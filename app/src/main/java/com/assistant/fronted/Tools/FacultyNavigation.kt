package com.assistant.fronted.Tools

import android.content.Context
import android.content.Intent
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.FacultyAffairActivity
import com.assistant.fronted.UI.Faculty.FacultyNotificationActivity
import com.assistant.fronted.UI.Faculty.FacultySettingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

object FacultyNavigation {
    /**
     * 初始化导航栏
     * @param id 设置当前选中的导航栏图标
     * @param context 上下文
     * @param bottomNavigationView 导航栏id
     */
    fun initialize(id: Int, context: Context, bottomNavigationView: BottomNavigationView){
        bottomNavigationView.selectedItemId = id
        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.Tnotification -> {
                    val intent = Intent(context,FacultyNotificationActivity::class.java)
                    context.startActivity(intent)
                    true
                }
                R.id.Taffair -> {
                    val intent = Intent(context,FacultyAffairActivity::class.java)
                    context.startActivity(intent)
                    true
                }
                R.id.Tsetting -> {
                    val intent = Intent(context,FacultySettingActivity::class.java)
                    context.startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}