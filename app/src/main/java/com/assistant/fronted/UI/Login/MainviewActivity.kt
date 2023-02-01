package com.assistant.fronted.UI.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assistant.fronted.UI.Faculty.FacultyNotificationActivity
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.UI.Faculty.Service.FacultyNotificationService
import com.assistant.fronted.UI.Faculty.User
import com.assistant.fronted.UI.Student.Service.StudentNotificationService
import com.assistant.fronted.UI.Student.StudentNotificationActivity
import com.assistant.fronted.UI.Student.StudentUser
import com.assistant.fronted.databinding.ActivityMainviewBinding
import com.google.android.material.tabs.TabLayout

class MainviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainviewBinding
    val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    private lateinit var studentServiceIntent: Intent
    private lateinit var serviceIntent: Intent

    /**
     * 标识学生或职工
     * true -> 学生
     * false -> 职工
     */
    private var identity: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 避免从桌面启动程序后，会重新实例化入口类的activity
        if (!this.isTaskRoot) { // 当前类不是该Task的根部，那么启动之前的页面
            val intent = intent
            if (intent != null) {
                val action = intent.action
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action) { // 当前类是从桌面启动的
                    finish() // finish掉该类，直接打开该Task中现存的Activity
                    return
                }
            }
        }


        studentServiceIntent = Intent(this, StudentNotificationService::class.java)
        serviceIntent = Intent(this, FacultyNotificationService::class.java)

        /**
         * 读取保存的用户密码
         */
        val userRemembered = getSharedPreferences("userRemember",Context.MODE_PRIVATE)
        val usernameRemembered = userRemembered.getString("user","none")
        val passwordRemembered = userRemembered.getString("password","none")
        identity = userRemembered.getBoolean("identity",true)

        if (!usernameRemembered.equals("none") && !passwordRemembered.equals("none")){
            binding.user.setText(usernameRemembered)
            binding.password.setText(passwordRemembered)
            binding.remember.isChecked = true

            val newPassword = intent.getStringExtra("EditPassword")
            if (newPassword != null){
                if (newPassword.isNotEmpty()){
                    val editor = userRemembered.edit()
                    editor.putString("password",newPassword)
                    editor.apply()
                    binding.password.setText(newPassword)
                }
            }
        }

        if (identity){
            binding.tabLayout.getTabAt(0)?.select()
        }else{
            binding.tabLayout.getTabAt(1)?.select()
        }

        /**
         * 身份切换
         */
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Log.d("tabSelected",tab?.text.toString())
                when(tab?.text.toString()){
                    "学生登录" -> {
                        identity = true
                        binding.textInputLayout.hint = "学号"
                    }
                    "职工登陆" -> {
                        identity = false
                        binding.textInputLayout.hint = "工号"
                    }
                    else -> identity = true
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                Log.d("tabUnselected",tab?.text.toString())
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
//                Log.d("tabReselected",tab?.text.toString())
            }
        })

        /**
         * 记住密码
         */
        binding.remember.setOnCheckedChangeListener { button, b ->
            if (button.isChecked){
                /**
                 * 向本地文件写入密码
                 */
                val editor = getSharedPreferences("userRemember", Context.MODE_PRIVATE).edit()
                editor.putString("user",binding.user.text.toString())
                editor.putString("password",binding.password.text.toString())
                editor.apply()
            }else{
                /**
                 * 删除本地文件数据
                 */
                val editor = getSharedPreferences("userRemember", Context.MODE_PRIVATE).edit()
                editor.putString("user","none")
                editor.putString("password","none")
                editor.apply()
            }
        }

        binding.password.addTextChangedListener {
            binding.remember.isChecked = false
        }

        /**
         * 登陆按钮
         */
        binding.facultyLogin.setOnClickListener {
            val user = User()
            user.no = binding.user.text.toString().toLong()
            user.password = binding.password.text.toString()
            viewModel.login(user,identity)
        }

        viewModel.studentLoginResult.observe(this, Observer { result ->
            when(result.code){
                "200" -> {
                    val userRemembered = getSharedPreferences("userRemember",Context.MODE_PRIVATE).edit()
                    userRemembered.putBoolean("identity",identity)
                    userRemembered.apply()

                    Log.d("LoginSuccess",result.data.toString())
                    StudentUser.setUser(result.data)

                    startService(studentServiceIntent)

                    val intent = Intent(this,StudentNotificationActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                "204" -> {
                    Log.d("LoginFail","学号不存在")
                    Toast.makeText(this,"学号不存在",Toast.LENGTH_LONG).show()
                }
                "203" -> {
                    Log.d("LoginFail","密码错误")
                    Toast.makeText(this,"密码错误",Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.facultyLoginResult.observe(this, Observer { result ->
            when(result.code){
                "200" -> {
                    val userRemembered = getSharedPreferences("userRemember",Context.MODE_PRIVATE).edit()
                    userRemembered.putBoolean("identity",identity)
                    userRemembered.apply()

                    Log.d("LoginSuccess",result.data.toString())
                    FacultyUser.setUser(result.data)

                    startService(serviceIntent)

                    val intent = Intent(this,FacultyNotificationActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                "204" -> {
                    Log.d("LoginFail","工号不存在")
                    Toast.makeText(this,"工号不存在",Toast.LENGTH_LONG).show()
                }
                "203" -> {
                    Log.d("LoginFail","密码错误")
                    Toast.makeText(this,"密码错误",Toast.LENGTH_LONG).show()
                }
            }
        })

        /**
         * 自动登录
         */
        val isLogout = intent.getBooleanExtra("Logout",false)
        if (!isLogout){
            if (!usernameRemembered.equals("none") && !passwordRemembered.equals("none")){
                val user = User()
                user.no = binding.user.text.toString().toLong()
                user.password = binding.password.text.toString()
                viewModel.login(user,identity)
            }
        }

    }
}