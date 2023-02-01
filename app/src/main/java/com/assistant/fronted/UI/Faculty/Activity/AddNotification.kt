package com.assistant.fronted.UI.Faculty.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.databinding.ActivityAddNotificationBinding
import com.assistant.fronted.network.MessageNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddNotification : AppCompatActivity() {

    lateinit var binding: ActivityAddNotificationBinding
    lateinit var job: Job

    /**
     * 本地暂存的在编辑的通知内容
     */
    lateinit var savedContent: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedContent = getSharedPreferences("SavedNotification",Context.MODE_PRIVATE)

        job = Job()
        val scope = CoroutineScope(job)

        /**
         * 顶部栏
         */
        binding.addNotificationTopBar.setNavigationOnClickListener {
            this.finish()
        }

        /**
        * 判断是否有本地暂存的内容
         */
        fillIfSaved()

        /**
         * 保存按钮
         */
        binding.addNotificationSave.setOnClickListener {
            if (saveContent()){
                Toast.makeText(this,"已保存",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"空",Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * 完成编辑插入按钮
         */
        binding.addNotificationAdd.setOnClickListener {
            scope.launch {
                if(insert()){
                    Log.d("NotificationInsert","Succeed")
                }else{
                    Log.d("NotificationInsert","Failure")
                }
            }
            clearSaved()
            this.finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun clearSaved(){
        val editor = savedContent.edit()
        editor.putString("Content","none")
        editor.apply()
    }

    private fun  fillIfSaved(){
        val content = savedContent.getString("Content","none")
        if (content != null && content != "none"){
            binding.contentInput.setText(content)
        }
    }

    private fun saveContent(): Boolean{
        val content = binding.contentInput.text.toString()
        if (content.isEmpty()){
            return false
        }

        val editor = savedContent.edit()
        if (content!!.isNotEmpty()){
            editor.putString("Content",content).apply()
            Log.d("Save",content)
            return true
        }
        return false
    }

    private suspend fun insert(): Boolean{
        val context = binding.contentInput.text.toString()
        if (context.isEmpty()){
            Toast.makeText(this,"不能为空哦",Toast.LENGTH_SHORT).show()
            return false
        }
        /**
         * 插入
         */
        val tno = FacultyUser.no
//        pid暂定
        val pid = System.currentTimeMillis()

        if (tno != null){
//            val result = MessageNetwork.insert(pid,tno,context,"none")

            FacultyUser.sendMessage(pid, tno, context)
            return true
        }

        return false
    }
}