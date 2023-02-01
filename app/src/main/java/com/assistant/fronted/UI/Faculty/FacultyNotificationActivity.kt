package com.assistant.fronted.UI.Faculty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.assistant.fronted.R
import com.assistant.fronted.Tools.FacultyNavigation
import com.assistant.fronted.UI.Faculty.Fragments.AffairFragment
import com.assistant.fronted.UI.Faculty.Fragments.NotificationFragment
import com.assistant.fronted.UI.Faculty.Fragments.SettingFragment
import com.assistant.fronted.UI.Faculty.Service.FacultyNotificationService
import com.assistant.fronted.UI.FacultyFragmentAdapter
import com.assistant.fronted.databinding.ActivityFacultyNotificationBinding

class FacultyNotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacultyNotificationBinding

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 底部导航栏与滑动切换页面
         */
        FacultyNavigation.initialize(R.id.Tnotification,binding.FacultyFragment,binding.facultyBottomNavigationFn)

        val listOfFragment = listOf<Fragment>(NotificationFragment(),AffairFragment(),SettingFragment())
        val viewpager2Adapter = FacultyFragmentAdapter(this,listOfFragment)
        binding.FacultyFragment.adapter = viewpager2Adapter
        binding.FacultyFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.facultyBottomNavigationFn.selectedItemId = when(position){
                    0 -> R.id.Tnotification
                    1 -> R.id.Taffair
                    2 -> R.id.Tsetting
                    else -> R.id.Tnotification
                }
                when(position){
                    0 -> binding.topbar.title = "浏览通知"
                    1 -> binding.topbar.title = "事务处理"
                    2 -> binding.topbar.title = "账户信息"
                    else -> binding.topbar.title = "浏览通知"
                }
                super.onPageSelected(position)
            }
        })

        /**
         * 开启后台service
         */
        serviceIntent = Intent(this,FacultyNotificationService::class.java)
        startService(serviceIntent)
    }

    override fun onResume() {
        super.onResume()
        startService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}