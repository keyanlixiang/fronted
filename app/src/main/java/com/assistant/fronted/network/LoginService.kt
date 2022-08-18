package com.assistant.fronted.network

import com.assistant.fronted.model.Faculty
import com.assistant.fronted.model.Student
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET("student/login")
    fun studentLogin(@Query("sno") sno:Long,@Query("spassword") spassword:String): Call<ResponseBody>

    @GET("faculty/login")
    fun facultyLogin(@Query("tno") tno:Long,@Query("tpassword") tpassword:String): Call<ResponseBody>

}