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
    private lateinit var netThread: NetWorkThread

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    companion object StopSelf{
        var self: Service? = null
        fun stopSelf_(){
            self?.stopSelf()
        }
    }

    override fun onCreate() {
        self = this
        super.onCreate()
        if (!this::webSocketClient.isInitialized){
            webSocketClient = MyWebSocket("ws://1.116.250.147:8282/webSocket/${FacultyUser.no}")
        }
        EventBus.getDefault().register(this)
        netThread = NetWorkThread(webSocketClient)
        netThread.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("FacultyServiceNet",webSocketClient.readyState.toString())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        if (this::webSocketClient.isInitialized){
            webSocketClient.closeBlocking()
        }
        netThread.exit = false
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun sendMessage(messageData: MessageData?){
        if (messageData!=null && this::webSocketClient.isInitialized){
            messageData.setMsgType(3)
            if (webSocketClient.readyState == ReadyState.OPEN){
                try {
                    webSocketClient.send(JSON.toJSONString(messageData))
                    Log.d("FacultyService","发送消息${JSON.toJSONString(messageData)}")
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    class NetWorkThread(var SocketClient: WebSocketClient) : Thread(){
        var exit: Boolean = true
        override fun run() {
            super.run()
            try {
                if (SocketClient.connectBlocking()) {
                    Log.i("FacultyService", "run: 连接服务器成功")
                } else {
                    Log.i("FacultyService", "run: 连接服务器失败")
                }
                while(exit){
                    Log.d("Faculty SERVICE NET",this.toString())
                    sleep(2000)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
        }
    }
}