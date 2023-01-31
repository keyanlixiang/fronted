package com.assistant.fronted.model

/**
 * 特别注明：
 * @param sacademy 意义为该学生所属辅导员工号（此前为所属学院）
 */
class Student {
    var sno: Long = 0
    var sname: String? = null
    var ssex: String? = null
    var sacademy: String? = null
    var smajor: String? = null
    var sclasss: String? = null
    var sgrade: String? = null
    var spassword: String? = null
    var spicture: String? = null
    override fun toString(): String {
        return "Student(sno=$sno, sname=$sname, ssex=$ssex, sacademy=$sacademy, smajor=$smajor, sclasss=$sclasss, sgrade=$sgrade, spassword=$spassword, spicture=$spicture)"
    }

}
