package com.assistant.fronted.UI.Faculty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assistant.fronted.R
import com.assistant.fronted.model.Message
import java.text.SimpleDateFormat

class NotificationAdapter(val data: List<Message>): RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.Title)
        val content: TextView = view.findViewById(R.id.content)
        val time: TextView = view.findViewById(R.id.Time)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = data[position]
        holder.time.text = SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(message.ptime)
        holder.content.text = message.pcontext
        holder.title.text = message.pfaculty.toString()
    }

    override fun getItemCount() = data.size

}