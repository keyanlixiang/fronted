package com.assistant.fronted.UI.Faculty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assistant.fronted.R
import com.assistant.fronted.Tools.FacultyNavigation
import com.assistant.fronted.databinding.ActivityFacultyNotificationBinding

class FacultyNotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacultyNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacultyNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacultyNavigation.initialize(R.id.Tnotification,this,binding.facultyBottomNavigationFn)
    }
}