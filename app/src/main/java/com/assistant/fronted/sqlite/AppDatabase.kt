package com.assistant.fronted.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assistant.fronted.model.Arrangment
import com.assistant.fronted.sqlite.DAO.ArrangmentDAO

@Database(version = 1, entities = [Arrangment::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun ArrangmentDAO(): ArrangmentDAO
    companion object {
        private var instance: AppDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "AppDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()     //开放主线程数据库操作权限
                .build().apply {
                    instance = this
                }
        }
    }
}