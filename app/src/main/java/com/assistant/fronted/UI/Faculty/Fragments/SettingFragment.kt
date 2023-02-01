package com.assistant.fronted.UI.Faculty.Fragments

import android.content.Intent
import android.media.metrics.Event
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.UI.Faculty.Service.FacultyNotificationService
import com.assistant.fronted.UI.Login.MainviewActivity
import com.assistant.fronted.databinding.FragmentSettingBinding
import com.assistant.fronted.model.Faculty
import com.assistant.fronted.model.Result
import com.assistant.fronted.network.LoginNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        job = Job()
        val scope = CoroutineScope(job)

        /**
         * 账号信息显示
         */
        binding.facultysettingShowId.text = FacultyUser.no.toString()
        binding.facultysettingShowName.text = FacultyUser.name
        binding.facultysettingShowAcademy.text = FacultyUser.unit
        binding.editPassword.setText(FacultyUser.password)

        /**
         * 修改密码
         */
        binding.editPasswordComfirm.setOnClickListener {
            scope.launch {
                val newPassword = binding.editPassword.text.toString()
                if (newPassword.isEmpty()){
                    Toast.makeText(context,"不能为空",Toast.LENGTH_SHORT).show()
                    return@launch
                }
                editPassword(newPassword)
            }
        }
        EventBus.getDefault().register(this)

        /**
         * 登出按钮
         */
        binding.logout.setOnClickListener {
            FacultyNotificationService.stopSelf_()
            val intent = Intent(activity,MainviewActivity::class.java)
            intent.putExtra("Logout",true)
            startActivity(intent)
            activity?.finish()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::job.isInitialized){
            job.cancel()
        }
    }

    private suspend fun editPassword(newPassword:String){
        val result = LoginNetwork.facultyUpdatePassword(FacultyUser.no!!, FacultyUser.password!!,newPassword)
        EventBus.getDefault().post(result)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEditPasswordCallBack(result: Result<Faculty>){
        if (result.isSuccess){
            Log.d("SettingFragment","修改密码成功")
            Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show()

            FacultyNotificationService.stopSelf_()

            val intent = Intent(activity,MainviewActivity::class.java)
            intent.putExtra("EditPassword",result.data?.tpassword)
            startActivity(intent)
            activity?.finish()
        }else{
            Log.d("SettingFragment","修改密码失败")
            Toast.makeText(context,"修改失败",Toast.LENGTH_SHORT).show()
        }
    }
}