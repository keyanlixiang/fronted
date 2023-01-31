package com.assistant.fronted.UI.Student.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.UI.Student.StudentUser
import com.assistant.fronted.UI.Student.WebSocket.MyWebSocket
import com.assistant.fronted.UI.Student.WebSocket.StudentWebSocket
import org.greenrobot.eventbus.EventBus
import org.java_websocket.client.WebSocketClient
import org.java_websocket.enums.ReadyState
import java.net.URISyntaxException

class StudentNotificationService : Service() {
    private lateinit var webSocketClient: WebSocketClient

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        try {
            if (!this::webSocketClient.isInitialized){
                webSocketClient = StudentWebSocket("ws://1.116.250.147:8282/webSocket/${StudentUser.no}")
            }
            if (webSocketClient.connectBlocking()) {
                Log.i("StudentService", "run: 连接服务器成功")
            } else {
                Log.i("StudentService", "run: 连接服务器失败")
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (webSocketClient.readyState != ReadyState.OPEN){
            try {
                if (webSocketClient.connectBlocking()) {
                    Log.i("StudentService", "reconnect: 连接服务器成功")
                } else {
                    Log.i("StudentService", "reconnect: 连接服务器失败")
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        if (this::webSocketClient.isInitialized){
            webSocketClient.closeBlocking()
        }
        super.onDestroy()
    }
}