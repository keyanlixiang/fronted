package com.assistant.fronted.UI.Student

import com.assistant.fronted.model.Student

object StudentUser {
    var no: Long? = null
    var name: String? = null
    var sex: String? = null
    var academy: String? = null
    var major: String? = null
    var sclass: String? = null
    var grade: String? = null
    var password: String? = null
    var picture: String? = null

    fun setUser(user: Student?){
        if (user != null){
            no = user.sno
            name = user.sname
            sex = user.ssex
            academy = user.sacademy
            major = user.smajor
            sclass = user.sclasss
            grade = user.sgrade
            password = user.spassword
            picture = user.spicture
        }
    }
}