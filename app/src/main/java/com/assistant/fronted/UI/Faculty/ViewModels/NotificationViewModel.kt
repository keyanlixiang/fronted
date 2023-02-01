package com.assistant.fronted.UI.Faculty.ViewModels

import android.util.Log
import androidx.lifecycle.*
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.UI.Student.WebSocket.MessageData
import com.assistant.fronted.model.Message
import com.assistant.fronted.network.MessageNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class NotificationViewModel: ViewModel() {
    private val message_mutable = MutableLiveData<Long>()
    val messageLiveData = Transformations.switchMap(message_mutable){
        getAllMessage()
    }

    private fun getAllMessage(): LiveData<List<Message>>{
        val result = liveData<List<Message>>(Dispatchers.IO){
            val rs: List<Message> = try {
                val answer =  MessageNetwork.getMessageByTno(FacultyUser.no!!)
                if (answer.isSuccess){
                    answer.data!!
                }else{
                    Log.d("getAllMessage","failure")
                    listOf<Message>()
                }
            }catch (e: Exception){
                e.printStackTrace()
                Log.d("getAllMessage","failure_exception")
                listOf<Message>()
            }
            emit(rs)
        }
        return result
    }

    fun registerEventBus(){
        EventBus.getDefault().register(this)
    }

    fun getAllMessage_(){
        message_mutable.value = System.currentTimeMillis()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshAfterInsert(notificationViewModelEventBus: NotificationViewModelEventBus){
        Log.d("NotificationViewModel","EventBus Triggered")
        getAllMessage_()
    }

    class NotificationViewModelEventBus(val messageData: MessageData,val type: Int){}
}