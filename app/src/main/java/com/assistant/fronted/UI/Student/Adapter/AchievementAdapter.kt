package com.assistant.fronted.UI.Student.Adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.assistant.fronted.R
import com.assistant.fronted.model.Achievement

class AchievementAdapter(val achievementList:List<Achievement>):RecyclerView.Adapter<AchievementAdapter.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val achieText:TextView=view.findViewById(R.id.achievement_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.adapter_achievement,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val achievement=achievementList[position]
        holder.achieText.text=achievement.acontext
    }

    override fun getItemCount(): Int =achievementList.size

}