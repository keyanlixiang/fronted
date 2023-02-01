package com.assistant.fronted.UI.Student.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Update
import com.assistant.fronted.R
import com.assistant.fronted.UI.Student.Activity.UploadAchievement
import com.assistant.fronted.databinding.FragmentAchievementBinding

class AchievementFragment : Fragment() {

    lateinit var binding:FragmentAchievementBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAchievementBinding.inflate(layoutInflater)
        binding.uploadButton.setOnClickListener{
            val intent=Intent(context,UploadAchievement::class.java)
            startActivity(intent)
        }
        return binding.root

    }


}