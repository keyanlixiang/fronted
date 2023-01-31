package com.assistant.fronted.sqlite.Repository

import com.assistant.fronted.Tools.AssistantApplication
import com.assistant.fronted.model.Achievement
import com.assistant.fronted.sqlite.AppDatabase
import com.assistant.fronted.sqlite.DAO.AchievementDAO

object AchievementRepository {
    lateinit var dao: AchievementDAO

    private fun initDao(){
        if (!this::dao.isInitialized){
            dao=AppDatabase.getDatabase(AssistantApplication.context).AchievementDAO()
        }
    }

    /**
     * 插入成就
     */
    fun insertAchievement(achievement: Achievement)= dao.insertAchievement(achievement)

    /**
     * 加载全部成就
     */
    fun getAchievement()= dao.getAll()


}