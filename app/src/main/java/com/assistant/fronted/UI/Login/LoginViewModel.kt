package com.assistant.fronted.UI.Login

import android.util.Log
import androidx.lifecycle.*
import com.assistant.fronted.UI.Faculty.User
import com.assistant.fronted.model.Faculty
import com.assistant.fronted.model.Result
import com.assistant.fronted.model.Student
import com.assistant.fronted.network.LoginNetwork
import kotlinx.coroutines.Dispatchers

class LoginViewModel: ViewModel() {
    private val loginResultMutable_faculty = MutableLiveData<User>()
    val facultyLoginResult = Transformations.switchMap(loginResultMutable_faculty){ user ->
        facultyLogin(user.no, user.password.toString())
    }

    private val loginResultMutable_student = MutableLiveData<User>()
    val studentLoginResult = Transformations.switchMap(loginResultMutable_student){ user ->
        studentLogin(user.no,user.password.toString())
    }

    /**
     * 登陆
     * @param user 用户
     * @param identity 身份
     */
    fun login(user: User,identity: Boolean){
        if (identity){
            Log.d("Login","Student Logging")
            loginResultMutable_student.value = user
        }else{
            Log.d("Login","Faculty Logging")
            loginResultMutable_faculty.value = user
        }
    }

    private fun facultyLogin(user: Long,password: String): LiveData<Result<Faculty>> {
        val resultLiveData = liveData<Result<Faculty>>(Dispatchers.IO){
            val result: Result<Faculty> = try {
                LoginNetwork.facultyLogin(user,password)
            }catch (e: Exception){
                Log.d("loginErr","FacultyLoginErr ${e.toString()}")
                Result<Faculty>()
            }
            emit(result)
        }
        return resultLiveData
    }

    private fun studentLogin(user: Long,password: String): LiveData<Result<Student>> {
        val resultLiveData = liveData<Result<Student>>(Dispatchers.IO){
            val result: Result<Student> = try {
                LoginNetwork.studentLogin(user,password)
            }catch (e: Exception){
                Log.d("loginErr","StudentLoginErr ${e.toString()}")
                Result<Student>()
            }
            emit(result)
        }
        return resultLiveData
    }

}