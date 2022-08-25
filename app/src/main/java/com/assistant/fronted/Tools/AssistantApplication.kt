package com.assistant.fronted.Tools

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 用以获取全局context
 */
class AssistantApplication: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}