package com.assistant.fronted.UI.Faculty.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.assistant.fronted.R
import com.assistant.fronted.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    lateinit var binding: FragmentNotificationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)


        return binding.root
    }
}