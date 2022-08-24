package com.assistant.fronted.network

import com.assistant.fronted.model.Faculty
import com.assistant.fronted.model.Result
import com.assistant.fronted.model.Student
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object LoginNetwork {

    private val loginService =ServiceCreator.create<LoginService>()

//    数据的接收
    suspend fun studentLogin(sno:Long,spassword:String)= loginService.studentLogin(sno,spassword).await()

    suspend fun facultyLogin(tno:Long,tpassword: String)=loginService.facultyLogin(tno,tpassword).await()

    suspend fun studentUpdatePassword(sno: Long,oldPassword:String,newPassword:String)= loginService.studentUpdatePassword(sno, oldPassword, newPassword).await()

    suspend fun facultyUpdatePassword(tno: Long,oldPassword:String,newPassword:String)= loginService.facultyUpdatePassword(tno, oldPassword, newPassword).await()




//     数据接收处理
    suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

//    将Result的data转换为对象
    fun parseStudent(data:String?):Student?{
        if (data.isNullOrEmpty()){
            return null;
        }
        return Gson().fromJson(data,Student::class.java)
    }

    fun parseFaculty(data: String?): Faculty? {
        if (data.isNullOrEmpty()){
            return null;
        }
        return Gson().fromJson(data,Faculty::class.java)
    }

}