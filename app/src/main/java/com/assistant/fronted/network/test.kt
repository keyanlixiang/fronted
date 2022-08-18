package com.assistant.fronted.network



//此文件为测试端口传输数据

suspend fun main(){


    val result=LoginNetwork.studentLogin(1001,"123456")
    print(result)
    println()

    val student=LoginNetwork.parseStudent(result?.data.toString())
    print(student)
    println()

    val result2=LoginNetwork.facultyLogin(1001,"12345")
    print(result2)
    println()

    val faculty=LoginNetwork.parseFaculty(result2?.data.toString())
    print(faculty)






}