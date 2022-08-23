package com.assistant.fronted.UI.Faculty

class User {
    var no: Long = 0
    var password: String? = null

    override fun toString(): String {
        return "Faculty(user=$no , password=$password)"
    }
}