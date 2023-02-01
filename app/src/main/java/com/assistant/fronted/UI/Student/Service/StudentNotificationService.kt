package com.assistant.fronted.UI.Student.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.assistant.fronted.R
import com.assistant.fronted.UI.Faculty.FacultyUser
import com.assistant.fronted.UI.Student.StudentUser
import com.assistant.fronted.UI.Student.WebSocket.MessageData
import com.assistant.fronted.UI.Student.WebSocket.MyWebSocket
import com.assistant.fronted.UI.Student.WebSocket.StudentWebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.java_websocket.client.WebSocketClient
import org.java_websocket.enums.ReadyState
import java.net.URISyntaxException

class StudentNotificationService : Service() {
    private lateinit var webSocketClient: WebSocketClient
    private lateinit var manager: NotificationManager
    private var notifyCount: Int = 0

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
        EventBus.getDefault().register(this)
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("UJSAS","UJS个人助手通知提醒",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun notifyMessage(messageData: notifySignal){
        Log.d("StudentService","EventBus Triggered")
        val notification = NotificationCompat.Builder(this,"UJSAS")
            .setContentTitle("新通知")
            .setContentText(messageData.messageData.getMsgData())
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_message_24))
            .build()
        manager.notify(notifyCount,notification)
        notifyCount++
    }

    class notifySignal(val messageData: MessageData){}
}