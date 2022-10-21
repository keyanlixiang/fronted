package com.assistant.fronted.network

import com.assistant.fronted.model.Message


//此文件为测试端口传输数据

suspend fun main(){

//  测试学生登录
//    val result=LoginNetwork.studentLogin(1001,"888888")
//    print(result)
//    println()

////  测试教职工登录
//    val result1=LoginNetwork.facultyLogin(1001,"123456")
//    print(result1)



//    消息测试
//    val re=MessageNetwork.get(1001)
//    print(re)

    val result1=MessageNetwork.getMessageByPid(1011)
    print(result1)
    println()

    val re=MessageNetwork.getMessageByTno(1001)
    print(re)
    println()

    val re1=MessageNetwork.delete(1005)
    print(re1)
    println()

    val re2=MessageNetwork.update(1011,"修改","修改")
    print(re2)
    println()

    val re3=MessageNetwork.insert(1006,1002,"消息","1")
    print(re3)
    println()






}