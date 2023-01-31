package com.assistant.fronted.sqlite.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.assistant.fronted.model.Achievement

@Dao
interface AchievementDAO {

    @Insert
    fun insertAchievement(achievement: Achievement)

    @Query("select * from achievement")
    fun getAll():List<Achievement>
}