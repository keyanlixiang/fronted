package com.assistant.fronted.model

class Faculty {
    private val tno: Long = 0
    private val tname: String? = null
    private val tsex: String? = null
    private val tunit: String? = null
    private val department: String? = null
    private val tpassword: String? = null
    private val tpicture: String? = null

    override fun toString(): String {
        return "Faculty(tno=$tno, tname=$tname, tsex=$tsex, tunit=$tunit, department=$department, tpassword=$tpassword, tpicture=$tpicture)"
    }

}
