package com.assistant.fronted.model

class Faculty {
    var tno: Long = 0
    var tname: String? = null
    var tsex: String? = null
    var tunit: String? = null
    var department: String? = null
    var tpassword: String? = null
    var tpicture: String? = null

    override fun toString(): String {
        return "Faculty(tno=$tno, tname=$tname, tsex=$tsex, tunit=$tunit, department=$department, tpassword=$tpassword, tpicture=$tpicture)"
    }

}
