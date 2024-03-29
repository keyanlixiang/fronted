package com.assistant.fronted.UI.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.assistant.fronted.R
import com.assistant.fronted.Tools.StudentNavigation
import com.assistant.fronted.UI.Faculty.Fragments.AffairFragment
import com.assistant.fronted.UI.Faculty.Fragments.NotificationFragment
import com.assistant.fronted.UI.Faculty.Fragments.SettingFragment
import com.assistant.fronted.UI.FacultyFragmentAdapter
import com.assistant.fronted.UI.Student.Fragments.AchievementFragment
import com.assistant.fronted.UI.Student.Fragments.ArrangeFragment
import com.assistant.fronted.UI.Student.Service.StudentNotificationService
import com.assistant.fronted.databinding.ActivityStudentNotificationBinding

class StudentNotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 底部导航栏与滑动切换页面
         */
        StudentNavigation.initialize(R.id.Tnotification,binding.StudentViewPager2,binding.studentBottomNavigationFn)

        val listOfFragment = listOf<Fragment>(
            com.assistant.fronted.UI.Student.Fragments.NotificationFragment(),
            ArrangeFragment(),
            com.assistant.fronted.UI.Student.Fragments.AffairFragment(),
            AchievementFragment()
        )
        val viewpager2Adapter = FacultyFragmentAdapter(this,listOfFragment)
        binding.StudentViewPager2.adapter = viewpager2Adapter
        binding.StudentViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.studentBottomNavigationFn.selectedItemId = when(position){
                    0 -> R.id.Snotification
                    1 -> R.id.Sarrange
                    2 -> R.id.Saffair
                    else -> R.id.Sachievement
                }
                when(position){
                    0 -> binding.topbar.title = "浏览通知"
                    1 -> binding.topbar.title = "日程安排"
                    2 -> binding.topbar.title = "账户信息"
                    else -> binding.topbar.title = "成就管理"
                }
                super.onPageSelected(position)
            }
        })
    }
}