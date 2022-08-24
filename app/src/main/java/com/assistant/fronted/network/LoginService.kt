package com.assistant.fronted.network


import com.assistant.fronted.model.Faculty
import com.assistant.fronted.model.Result
import com.assistant.fronted.model.Student
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET("student/login")
    fun studentLogin(@Query("sno") sno:Long,@Query("spassword") spassword:String): Call<Result<Student>>

    @GET("faculty/login")
    fun facultyLogin(@Query("tno") tno:Long,@Query("tpassword") tpassword:String): Call<Result<Faculty>>

    @GET("student/updatePassword")
    fun studentUpdatePassword(@Query("sno") sno:Long,@Query("oldPassword") oldPassword:String,@Query("newPassword") newPassword:String):Call<Result<Student>>

    @GET("faculty/updatePassword")
    fun facultyUpdatePassword(@Query("tno") tno:Long,@Query("oldPassword") oldPassword:String,@Query("newPassword") newPassword:String):Call<Result<Faculty>>

}