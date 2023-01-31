package com.assistant.fronted.UI.Faculty

import com.assistant.fronted.UI.Student.WebSocket.MessageData
import com.assistant.fronted.model.Faculty
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat

/**
 * @param no 工号
 * @param name 姓名
 * @param sex 性别
 * @param unit 行政单位
 * @param department 系别/科室
 * @param password 密码
 * @param picture 头像/照片
 */
object FacultyUser {
    var no: Long? = null
    var name: String? = null
    var sex: String? = null
    var unit: String? = null
    var department: String? = null
    var password: String? = null
    var picture: String? = null

    var messageData: MessageData? = null

    fun setUser(user: Faculty?){
        if (user != null){
            no = user.tno
            name = user.tname
            sex = user.tsex
            unit = user.tunit
            department = user.department
            password = user.tpassword
            picture = user.tpicture
        }
    }

    fun sendMessage(pid:Long,tno:Long,context:String){
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
        messageData = MessageData()
        messageData?.apply {
            setFromUserId(tno.toString())
            setMsgData(context)
            setTime(time)
        }

        EventBus.getDefault().post(messageData)
    }
}