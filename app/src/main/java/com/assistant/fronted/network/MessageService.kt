package com.assistant.fronted.network

import com.assistant.fronted.model.Message
import com.assistant.fronted.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MessageService {



    @GET("message/getByPid")
    fun getMessageByPid(@Query("pid") pid:Long) : Call<Result<Message>>

    /**
     * 获取导员发布的全部信息
     */
    @GET("message/getByTno")
    fun getMessageByTno(@Query("tno") tno:Long) : Call<Result<List<Message>>>

    @GET("message/update")
    fun update(@Query("pid") pid:Long, @Query("newContext") newContext:String, @Query("newAnnex") newAnnex:String) : Call<Result<Message>>

    @GET("message/delete")
    fun delete(@Query("pid") pid:Long) : Call<Result<Message>>

    @GET("message/insert")
    fun insert(@Query("pid") pid:Long,@Query("tno") tno: Long,@Query("context") context:String,@Query("annex") annex:String) : Call<Result<Message>>

}