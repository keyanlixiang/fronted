package com.assistant.fronted.UI.Student.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assistant.fronted.R
import com.assistant.fronted.UI.Student.Fragments.ArrangeFragment
import com.assistant.fronted.databinding.ActivityDialogBinding
import com.assistant.fronted.sqlite.Repository.ArrangeRepotistory

class DialogActivity : AppCompatActivity() {
    lateinit var binding: ActivityDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insertarrange.setOnClickListener {
            val arrangeContent = binding.arrangeContentInput.text.toString()
            val date = intent.getLongExtra("date",0L)
            if (arrangeContent.isNotEmpty() && date != 0L){
                ArrangeRepotistory.insertArrange(date, arrangeContent)
            }
            ArrangeFragment.AFrefresh.refresh()
            finish()
        }
    }
}