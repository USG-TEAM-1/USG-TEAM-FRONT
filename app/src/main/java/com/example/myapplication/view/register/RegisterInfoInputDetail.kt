package com.example.myapplication.view.register

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavController
import com.example.myapplication.data.BookItemIsbn
import android.net.Uri
import okhttp3.MultipartBody
import java.io.File


object RegisterInfoInputDetail {


    @Composable
    fun view(isbnCode: String, bookItemIsbn: BookItemIsbn, navController: NavController) {
        val PICK_IMAGES_REQUEST_CODE = 1
        var imageUris by remember { mutableStateOf(listOf<Uri>()) }

        Log.d("detail",isbnCode)
        Column {
            InfoForIsbnComponent(bookItemIsbn)
            Divider()
            TitleTextField()
            Button(onClick = { /*pickImages()*/ }) {
                Text("이미지 선택")
            }
            InfoDetailLine(title = "판매가")
            InfoDetailLine(title = "상세정보")
            Button(onClick = { /*uploadImages()*/ }) {
                Text("확인")
            }
        }
    }

//    fun uploadImages() {
//        val imageFiles = ArrayList<MultipartBody.Part>()
//        for ((index, uri) in imageUris.withIndex()) {
//            val file = File(getRealPathFromUri(uri))
//            val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
//            val body = MultipartBody.Part.createFormData("images[$index]", file.name, requestFile)
//            imageFiles.add(body)
//        }
//
//        val service = retrofit.create(ApiService::class.java)
//        val call = service.uploadBook(bookName, bookComment, bookPostName, bookPrice, isbn, bookRealPrice, author, publisher, imageFiles)
//        call.enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                // 업로드 성공 처리
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                // 업로드 실패 처리
//            }
//        })
//    }

    @Composable
    private fun InfoForIsbnComponent(bookItemIsbn: BookItemIsbn) = Column {
        InfoForIsbnLine(title = "책 제목", content = bookItemIsbn.bookName)
        InfoForIsbnLine(title = "저자", content = bookItemIsbn.author)
        InfoForIsbnLine(title = "출판사", content = bookItemIsbn.publisher)
    }

    @Composable
    fun InfoForIsbnLine(title: String, content: String) = Row {
        Text(text = title)
        InfoForIsbnText(text = content)
    }

    @Composable
    private fun InfoForIsbnText(text: String) {
        Text(text)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TitleTextField() {
        var title by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = title,
            onValueChange = { newText -> title = newText },
            singleLine = true,
            placeholder = { Text("제목을 입력하시오") }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InfoDetailLine(title: String) = Row {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        Text(title)
        TextField(
            value = text,
            onValueChange = { newText -> text = newText }
        )
    }
}