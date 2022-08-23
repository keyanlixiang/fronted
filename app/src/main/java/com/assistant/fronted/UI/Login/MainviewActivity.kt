package com.assistant.fronted.UI.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.assistant.fronted.R
import com.assistant.fronted.databinding.ActivityMainviewBinding

class MainviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainviewBinding
    val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.facultyLogin.setOnClickListener {

        }

    }
}