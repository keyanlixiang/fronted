package com.assistant.fronted.UI.Student.WebSocket

import android.util.Log
import com.assistant.fronted.UI.Faculty.ViewModels.NotificationViewModel
import com.assistant.fronted.UI.Student.Service.StudentNotificationService
import com.assistant.fronted.UI.Student.ViewModel.StudentNotificationViewModel
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import kotlin.concurrent.thread

class StudentWebSocket(url:String) : WebSocketClient(URI(url)) {

    private val TAG="StudentWebSocket"

    /*
         *
         * 打开webSocket时回调
         * */
    override fun onOpen(serverHandshake: ServerHandshake?) {
        Log.i(TAG, "onOpen: 打开webSocket连接")
    }


    /*
         *
         * 接收到消息时回调
         * */
    override fun onMessage(s: String) {
        val gson = Gson()
        val messageData: MessageData = gson.fromJson(s, MessageData::class.java)
        Log.i(TAG, "学生收到消息$s")
        Log.i("message", messageData.toString())
        EventBus.getDefault().post(StudentNotificationViewModel.NotificationViewModelEventBus(messageData,messageData.getMsgType()))
        EventBus.getDefault().post(StudentNotificationService.notifySignal(messageData))
    }

    /*
         *
         * 断开连接时回调
         * */
    override fun onClose(i: Int, s: String?, b: Boolean) {
        Log.i(TAG, "断开webSocket连接")
    }

    /*
         *
         * 出现异常时回调
         * */
    override fun onError(e: Exception?) {
        Log.i(TAG,"发送错误，连接断开")
        thread{ reconnectBlocking() }.start()
    }
}