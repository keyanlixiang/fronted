package com.assistant.fronted.UI.Faculty.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.alibaba.fastjson.JSON
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.UI.Student.WebSocket.MessageData
import com.assistant.fronted.UI.Student.WebSocket.MyWebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.java_websocket.client.WebSocketClient
import org.java_websocket.enums.ReadyState
import java.net.URISyntaxException

class FacultyNotificationService : Service() {
    private lateinit var webSocketClient: WebSocketClient

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            if (!this::webSocketClient.isInitialized){
                webSocketClient = MyWebSocket("ws://10.0.2.2:8080/webSocket/${FacultyUser.no}")
            }
            if (webSocketClient.connectBlocking()) {
                Log.i("FacultyService", "run: 连接服务器成功")
            } else {
                Log.i("FacultyService", "run: 连接服务器失败")
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        EventBus.getDefault().register(this)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        if (this::webSocketClient.isInitialized){
            webSocketClient.closeBlocking()
        }
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun sendMessage(messageData: MessageData?){
        if (messageData!=null && this::webSocketClient.isInitialized){
            if (webSocketClient.readyState == ReadyState.OPEN){
                webSocketClient.send(JSON.toJSONString(messageData))
                Log.d("FacultyService","发送消息${JSON.toJSONString(messageData)}")
            }
        }
    }
}