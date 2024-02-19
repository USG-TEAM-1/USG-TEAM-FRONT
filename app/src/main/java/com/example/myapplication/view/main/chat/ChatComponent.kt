package com.example.myapplication.view.main.chat

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent

object ChatComponent {

    private lateinit var stompClient: StompClient
    private val receivedMessages = mutableStateListOf<ChatMessage>()

    @Composable
    fun view() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReceivedMessages()
            SendMessage()
            connectToWebSocket() // 페이지를 들어오자마자 서버에 연결
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ReceivedMessages() {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Received Messages:")
            for (message in receivedMessages) {
                Text("- Message: ${message.message}, Sender: ${message.senderId}," +
                        " Receiver: ${message.receiverId}, Timestamp: ${message.timestamp}"+
                " ChatRoom: ${message.chatRoomId}")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SendMessage() {
        val context = LocalContext.current
        val (message, setMessage) = remember { mutableStateOf("") }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = message,
                onValueChange = setMessage,
                label = { Text("메시지 입력") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { sendMessage(context, message) })
            )
            Button(onClick = { sendMessage(context, message) }) {
                Text("전송")
            }
        }
    }

    private fun connectToWebSocket() {
        val serverUrl = "ws://10.0.2.2:9090/ws" // 서버 URL
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, serverUrl)
        stompClient.connect() // 서버 연결
        val chatRoomId = 1 // 채팅방 ID
        val destination = "/topic/chat/$chatRoomId"

        // 서버 연결 후에 메시지를 가져오고, 새로운 메시지를 받을 때마다 화면에 업데이트
        stompClient.lifecycle().subscribe { lifecycleEvent ->
            if (lifecycleEvent.type == LifecycleEvent.Type.OPENED) {
                // 서버 연결이 성공적으로 열린 경우에만 메시지를 가져오고, 구독 시작
                fetchMessages()
                stompClient.topic(destination).subscribe() { topicmessage ->
                    // 서버로부터 메시지 수신 시 처리
                    val messageJson = topicmessage.payload
                    val messageData = parseMessageData(messageJson)
                    receivedMessages.add(messageData)
                }
            }
        }
    }
    private fun parseMessageData(messageJson: String): ChatMessage {
        val gson = Gson()
        val responseData = gson.fromJson(messageJson, ResponseData::class.java)
        return responseData.body.data
    }
    // 주어진 JSON 데이터에 대한 데이터 클래스 정의
    data class ResponseData(
        @SerializedName("body")
        val body: BodyData
    )
    data class BodyData(
        @SerializedName("data")
        val data: ChatMessage
    )
    data class ChatMessage(
        @SerializedName("message")
        val message: String,
        @SerializedName("senderId")
        val senderId: Long,
        @SerializedName("receiverId")
        val receiverId: Long,
        @SerializedName("timestamp")
        val timestamp: String,
        @SerializedName("chatRoomId")
        val chatRoomId: Long
    )

    data class MessageResponse(
        val data: List<ChatMessage>,
        val message: String?
    )

    private fun fetchMessages() {
        val senderId = "1"
        val receiverId = "2"

        // Retrofit 인스턴스 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:9090/") // 서버의 기본 URL
            .addConverterFactory(GsonConverterFactory.create()) // JSON 응답을 자바 객체로 변환하는 컨버터 팩토리 추가
            .build()

        // Retrofit 인터페이스 구현
        val service = retrofit.create(MessageService::class.java)

        // API 호출
        val call = service.getMessages(senderId, receiverId)
        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    val messages = response.body()?.data
                    // 메시지를 성공적으로 가져왔을 때의 처리
                    messages?.let {
                        for (messageData in it) {
                            // ChatMessage 객체를 생성하여 receivedMessages에 추가
                            receivedMessages.add(ChatMessage(
                                messageData.message,
                                messageData.senderId,
                                messageData.receiverId,
                                messageData.timestamp,
                                messageData.chatRoomId
                            ))
                        }
                    }
                } else {
                    // 서버 응답이 실패했을 때의 처리
                    Log.e("API Error", "Failed to fetch messages: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                // API 호출 자체가 실패했을 때의 처리
                Log.e("API Error", "Failed to fetch messages", t)
            }
        })
    }

    //메시지 api처리로 받아오는 것.
    interface MessageService {
        @GET("/api/messages")
        fun getMessages(
            @Query("senderId") senderId: String,
            @Query("receiverId") receiverId: String
        ): Call<MessageResponse>
    }

    private fun sendMessage(context: android.content.Context, message: String) {
        val jsonObject = JSONObject().apply {
            put("message", message)
            put("senderId", "1")
            put("receiverId", " 2")
            put("chatRoomId", 1L)
        }
        Log.e("메시지 데이터", jsonObject.toString())

        // 서버에 SEND 메시지 전송
        stompClient.send("/app/chat/1", jsonObject.toString()).subscribe()
    }
}