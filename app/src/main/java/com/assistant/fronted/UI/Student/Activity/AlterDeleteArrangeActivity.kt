package com.assistant.fronted.UI.Student.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.assistant.fronted.R
import com.assistant.fronted.UI.Student.Fragments.ArrangeFragment
import com.assistant.fronted.databinding.ActivityAlterDeleteArrangeBinding
import com.assistant.fronted.sqlite.Repository.ArrangeRepotistory

class AlterDeleteArrangeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlterDeleteArrangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlterDeleteArrangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("ID",-1)
        if (id == -1){
            Toast.makeText(this,"获取对应安排失败",Toast.LENGTH_SHORT)
            finish()
        }
        val arrange = ArrangeRepotistory.getArrangeFromID(id)
        binding.arrangeContentAlter.setText(arrange.content)

        binding.alterarrange.setOnClickListener {
            ArrangeRepotistory.updateArrange(binding.arrangeContentAlter.text.toString(),id)
            ArrangeFragment.refresh()
            finish()
        }
        binding.deleteButton.setOnClickListener {
            ArrangeRepotistory.deleteFromId(id)
            ArrangeFragment.refresh()
            finish()
        }
    }
}