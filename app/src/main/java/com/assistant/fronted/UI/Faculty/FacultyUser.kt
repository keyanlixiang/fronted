package com.assistant.fronted.UI.Faculty

import com.assistant.fronted.model.Faculty

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
}