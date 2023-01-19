package com.assistant.fronted.UI.Student.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.assistant.fronted.R
import com.assistant.fronted.model.Arrangment
import com.assistant.fronted.sqlite.Repository.ArrangeRepotistory
import com.google.android.material.divider.MaterialDivider

class ArrangeAdapter(val data: List<Arrangment>): RecyclerView.Adapter<ArrangeAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val checkBox: CheckBox = view.findViewById(R.id.arrangeitem)
        val divider: MaterialDivider = view.findViewById(R.id.arrangedivider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_arrange,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrange = data[position]

        holder.checkBox.text = arrange.content
        if (arrange.isdone == 1){
            holder.divider.visibility = View.VISIBLE
        }else{
            holder.divider.visibility = View.INVISIBLE
        }
        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                holder.divider.visibility = View.VISIBLE
                ArrangeRepotistory.setIsdone(1,arrange.id)
            }else{
                holder.divider.visibility = View.INVISIBLE
                ArrangeRepotistory.setIsdone(0,arrange.id)
            }
        }
    }

    override fun getItemCount() = data.size
}