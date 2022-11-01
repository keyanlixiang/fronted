package com.assistant.fronted.UI.Faculty.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.Activity.AddNotification
import com.assistant.fronted.UI.Faculty.NotificationAdapter
import com.assistant.fronted.UI.Faculty.ViewModels.NotificationViewModel
import com.assistant.fronted.databinding.FragmentNotificationBinding
import com.assistant.fronted.model.Message

class NotificationFragment : Fragment() {

    lateinit var binding: FragmentNotificationBinding

    val viewModel by lazy { ViewModelProvider(this).get(NotificationViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)

        /**
         * 新建消息按钮
         */
        binding.newMessage.setOnClickListener {
            val intent = Intent(activity,AddNotification::class.java)
            startActivity(intent)
        }

        /**
         * recyclerview数据绑定
         */
        viewModel.getAllMessage_()
        activity?.let { viewModel.messageLiveData.observe(it, Observer { data ->
            bindData_recyclerView(data.sortedByDescending { message -> message.ptime })
        }) }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllMessage_()
    }

    private fun bindData_recyclerView(data: List<Message>){
        val layoutManager = LinearLayoutManager(this.context)
        val adapter = NotificationAdapter(data)
        binding.messageShow.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }
}