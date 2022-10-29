package com.assistant.fronted.UI.Faculty.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.Activity.AddNotification
import com.assistant.fronted.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    lateinit var binding: FragmentNotificationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)

        /**
         * 新建消息按钮
         */
        binding.newMessage.setOnClickListener {
            val intent = Intent(activity,AddNotification::class.java)
            startActivity(intent)
        }



        return binding.root
    }
}