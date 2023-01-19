package com.assistant.fronted.UI.Student.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assistant.fronted.R
import com.assistant.fronted.UI.Student.Activity.DialogActivity
import com.assistant.fronted.UI.Student.Adapter.ArrangeAdapter
import com.assistant.fronted.UI.Student.ViewModel.ArrangeViewModel
import com.assistant.fronted.databinding.FragmentArrangeBinding
import com.assistant.fronted.sqlite.Repository.ArrangeRepotistory
import java.text.SimpleDateFormat
import java.util.*

class ArrangeFragment : Fragment() {
    private lateinit var viewModel: ArrangeViewModel
    lateinit var binding: FragmentArrangeBinding
    var date: Long = 0L

    companion object AFrefresh{
        var fragment: ArrangeFragment? = null
        fun refresh(){
            fragment?.viewModel?.refreshFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArrangeBinding.inflate(layoutInflater)
        binding.datepicker.initialize(activity as AppCompatActivity)
        viewModel = ViewModelProvider(this).get(ArrangeViewModel::class.java)
        AFrefresh.fragment = this

        val today = System.currentTimeMillis()
        date = today
        val todaysArrange = ArrangeRepotistory.getArrangeFromDate(today)
        val layoutManager = LinearLayoutManager(context)
        val adapter = ArrangeAdapter(todaysArrange)
        binding.arrangelist.layoutManager = layoutManager
        binding.arrangelist.adapter = adapter

        binding.datepicker.setOnDateClickListener { date: Long ->
            this.date = date
            val data = ArrangeRepotistory.getArrangeFromDate(date)
            val layoutManager = LinearLayoutManager(context)
            val adapter = ArrangeAdapter(data)
            binding.arrangelist.layoutManager = layoutManager
            binding.arrangelist.adapter = adapter
        }

        binding.addarrange.setOnClickListener {
            val intent = Intent(activity,DialogActivity::class.java)
            intent.putExtra("date",date)
            startActivity(intent)
        }

        viewModel.refreshSignal.observe(viewLifecycleOwner, androidx.lifecycle.Observer { signal->
            Log.d("OBSERVE","refresh")
            val data = ArrangeRepotistory.getArrangeFromDate(date)
            val layoutManager = LinearLayoutManager(context)
            val adapter = ArrangeAdapter(data)
            binding.arrangelist.layoutManager = layoutManager
            binding.arrangelist.adapter = adapter

            binding.datepicker.refreshAffair()
        })
        return binding.root
    }
}