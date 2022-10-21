package com.assistant.fronted.model

import java.sql.Timestamp


class Message {
    var pid: Long = 0
    var pfaculty: Long = 0
    var ptime: Timestamp? = null
    var pcontext: String? = null
    var annex: String? = null

    override fun toString(): String {
        return "Message(pid=$pid, pfaculty=$pfaculty, ptime=$ptime, pcontext=$pcontext, annex=$annex)"
    }

}