package com.assistant.fronted.sqlite.Repository

import android.util.Log
import com.assistant.fronted.Tools.AssistantApplication
import com.assistant.fronted.model.Arrangment
import com.assistant.fronted.sqlite.AppDatabase
import com.assistant.fronted.sqlite.DAO.ArrangmentDAO
import java.text.SimpleDateFormat
import java.util.*

object ArrangeRepotistory {
    lateinit var dao: ArrangmentDAO

    private fun initDao(){
        if (!this::dao.isInitialized){
            dao =AppDatabase.getDatabase(AssistantApplication.context).ArrangmentDAO()
        }
    }

    /**
     * 插入日程
     */
    fun insertArrange(date: Long,content: String){
        initDao()
        val date_ = SimpleDateFormat("yyyy-MM-dd").format(Date(date))
        val arrangment = Arrangment(date_,content,0)
        Log.d("DATE",date_)
        dao.insertArrange(arrangment)
    }

    /**
     * 根据日期时间戳获取日程表
     */
    fun getArrangeFromDate(date: Long): List<Arrangment>{
        initDao()
        val date_ = SimpleDateFormat("yyyy-MM-dd").format(Date(date))
        return dao.getArrangeFromDate(date_)
    }

    /**
     * 设置某日程是否已完成
     */
    fun setIsdone(isdone: Int,id: Int){
        initDao()
        dao.setIsdone(isdone, id)
    }

    /**
     * 删除某日程
     */
    fun deleteFromId(id: Int){
        initDao()
        dao.deleteFromId(id)
    }

    /**
     * 获取所有
     */
    fun getAll():List<Arrangment>{
        initDao()
        return dao.getAll()
    }

    /**
     * 根据ID获取对应安排
     */
    fun getArrangeFromID(id: Int): Arrangment{
        initDao()
        return dao.getArrangeFromID(id)
    }

    /**
     * 修改某个安排
     */
    fun updateArrange(content:String, id: Int){
        initDao()
        return dao.updateArrange(content, id)
    }
}