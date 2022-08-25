package com.assistant.fronted.UI.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.FacultyNotificationActivity
import com.assistant.fronted.UI.Faculty.User
import com.assistant.fronted.databinding.ActivityMainviewBinding
import com.google.android.material.tabs.TabLayout

class MainviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainviewBinding
    val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

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

        /**
         * 读取保存的用户密码
         */
        val userRemembered = getSharedPreferences("userRemember",Context.MODE_PRIVATE)
        val usernameRemembered = userRemembered.getString("user","none")
        val passwordRemembered = userRemembered.getString("password","none")
        if (!usernameRemembered.equals("none") && !passwordRemembered.equals("none")){
            binding.user.setText(usernameRemembered)
            binding.password.setText(passwordRemembered)
            binding.remember.isChecked = true
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
                    Log.d("LoginSuccess",result.data.toString())
                }
                "204" -> {
                    Log.d("LoginFail","学号不存在")
                }
                "204" -> {
                    Log.d("LoginFail","密码错误")
                }
            }
        })

        viewModel.facultyLoginResult.observe(this, Observer { result ->
            when(result.code){
                "200" -> {
                    Log.d("LoginSuccess",result.data.toString())
                    val intent = Intent(this,FacultyNotificationActivity::class.java)
                    startActivity(intent)
                }
                "204" -> {
                    Log.d("LoginFail","工号不存在")
                }
                "204" -> {
                    Log.d("LoginFail","密码错误")
                }
            }
        })

    }
}