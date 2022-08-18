package com.assistant.fronted.model

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
