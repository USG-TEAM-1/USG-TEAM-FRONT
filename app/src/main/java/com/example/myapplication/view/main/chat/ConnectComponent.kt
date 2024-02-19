import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.view.main.chat.ChatComponent
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val BASE_URL = "ws://10.0.2.2:9090/ws"
private const val API_BASE_URL = "http://10.0.2.2:9090"

object ConnectComponent {
    @Composable
    fun view(){
        ConnectScreen()
    }

    @Composable
    fun ConnectScreen() {
        val context = LocalContext.current
        val (connected, setConnected) = remember { mutableStateOf(false) }

        if (connected) {
            // 연결이 성공하면 ChatComponent로 이동
            ChatComponent.view()
            Toast.makeText(context, "연결에 성공했습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { connectToSTOMP(context, setConnected) }) {
                    Text("Connect")
                }
            }
        }
    }

    private fun connectToSTOMP(context: Context, setConnected: (Boolean) -> Unit) {
        try {
            val client = OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build()

            val request = Request.Builder()
                .url(BASE_URL)
                .build()

            val listener = object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    super.onOpen(webSocket, response)
                    // WebSocket 연결이 성공하면 연결 상태를 설정
                    setConnected(true)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    super.onFailure(webSocket, t, response)
                    // 연결 실패 시 처리
                    (context as? Activity)?.runOnUiThread {
                        Toast.makeText(context, "연결에 실패했습니다. 다시 시도해주세요1.", Toast.LENGTH_SHORT).show()
                        Log.e("WebSocket", "WebSocket 연결 실패: ${t.message}")
                    }
                }
            }

            // ChatRoom 생성 및 ID 조회 API 호출
            val requestBody = ChatRoomRequest(opponentId = 2L) // 수정 필요
            val createRequest = Request.Builder()
                .url(API_BASE_URL + "/chat-rooms")
                .post(RequestBody.create(MediaType.parse("application/json"), Gson().toJson(requestBody)))
                .build()

            client.newWebSocket(request, listener)

            // ChatRoom 생성 및 ID 조회 API 호출 후에 WebSocket 연결 시도
            OkHttpClient.Builder().build().newCall(createRequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // 실패 시 처리
                    Log.e("WebSocket", "ChatRoom 생성 실패: ${e.message}")
                    (context as? Activity)?.runOnUiThread {
                        Toast.makeText(context, "채팅방 생성에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    // 응답 처리
                    if (response.isSuccessful) {
                        // ChatRoom 생성 및 ID 조회 성공 시 WebSocket 연결 시도
                        client.newWebSocket(request, listener)
                    } else {
                        Log.e("WebSocket", "ChatRoom 생성 실패: ${response.code()}")
                    }
                }
            })
        } catch (e: Exception) {
            // 예외 처리
            Log.e("WebSocket", "WebSocket 연결 실패: ${e.message}")
            (context as? Activity)?.runOnUiThread {
                Toast.makeText(context, "연결에 실패했습니다. 다시 시도해주세요2.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    data class ChatRoomRequest(
        val opponentId: Long
    )

    data class ChatRoomRes(
        val roomId: Long
    )
}
