package com.assistant.fronted.UI.Faculty.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assistant.fronted.R
import com.assistant.fronted.databinding.ActivityAddNotificationBinding

class AddNotification : AppCompatActivity() {

    lateinit var binding: ActivityAddNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}