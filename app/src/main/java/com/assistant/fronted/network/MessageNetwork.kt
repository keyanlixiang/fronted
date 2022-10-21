package com.assistant.fronted.network


import com.assistant.fronted.network.LoginNetwork.await


object MessageNetwork {

    private val messageService=ServiceCreator.create<MessageService>()


    suspend fun getMessageByPid(pid:Long)= messageService.getMessageByPid(pid).await()

    suspend fun getMessageByTno(tno:Long)= messageService.getMessageByTno(tno).await()

    suspend fun delete(pid:Long)= messageService.delete(pid).await()

    suspend fun insert(pid: Long,tno: Long,context: String,annex: String)= messageService.insert(pid, tno, context, annex).await()

    suspend fun update(pid: Long,newContext: String,newAnnex:String)= messageService.update(pid, newContext, newAnnex).await()





}