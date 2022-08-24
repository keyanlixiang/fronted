package com.assistant.fronted.network

import com.assistant.fronted.model.Faculty
import com.assistant.fronted.model.Student
import com.assistant.fronted.network.LoginNetwork.await


//此文件为测试端口传输数据

suspend fun main(){

////  测试学生登录
//    val result=LoginNetwork.studentLogin(1002,"123456")
//    print(result)
//    println()
//    val student: Student? =result.data
//    student?.let {
//        print(it.sno)
//        print(" ")
//        print(it.sname)
//        println()
//    }
//
////  测试教职工登录
//    val result1=LoginNetwork.facultyLogin(1001,"123456")
//    print(result1)
//    println()
//    val faculty:Faculty?=result1.data
//    faculty?.let {
//        print(it.tno)
//        print(" ")
//        print(it.tname)
//        println()
//    }


//  测试修改密码
    val result=LoginNetwork.studentUpdatePassword(1001,"123456","888888")
    print(result)
    println()

    val result1=LoginNetwork.studentUpdatePassword(1001,"123456","888888")
    print(result1)
    println()

    val result2=LoginNetwork.facultyUpdatePassword(1001,"123456","888888")
    print(result2)
    println()

    val result3=LoginNetwork.facultyUpdatePassword(1001,"123456","888888")
    print(result3)
    println()


}