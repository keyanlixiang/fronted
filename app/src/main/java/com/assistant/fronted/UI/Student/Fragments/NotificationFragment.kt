package com.assistant.fronted.UI.Student.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.Adapter.NotificationAdapter
import com.assistant.fronted.UI.Student.ViewModel.StudentNotificationViewModel
import com.assistant.fronted.databinding.FragmentNotification2Binding
import com.assistant.fronted.model.Message

class NotificationFragment : Fragment() {
    lateinit var binding: FragmentNotification2Binding
    val viewModel by lazy { ViewModelProvider(this).get(StudentNotificationViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotification2Binding.inflate(layoutInflater)

        viewModel.getAllMessage_()
        viewModel.messageLiveData.observe(viewLifecycleOwner){
            bindData_recyclerView(it.sortedByDescending { message: Message -> message.ptime })
            binding.refreshProgressbar.visibility = View.INVISIBLE
        }
        viewModel.registerEventBus()

        /**
         * 刷新按钮
         */
        binding.refreshFloatingActionButton.setOnClickListener {
            viewModel.getAllMessage_()
            binding.refreshProgressbar.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllMessage_()
    }

    private fun bindData_recyclerView(data: List<Message>){
        val layoutManager = LinearLayoutManager(this.context)
        val adapter = NotificationAdapter(data)
        binding.studentNotificationRecyclerView.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }
}