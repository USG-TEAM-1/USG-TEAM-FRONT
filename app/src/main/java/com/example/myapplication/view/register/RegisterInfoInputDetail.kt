package com.example.myapplication.view.register

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
import androidx.navigation.NavController
import com.example.myapplication.data.BookItemIsbn

object RegisterInfoInputDetail {
    @Composable
    fun view(isbnCode: String, bookItemIsbn: BookItemIsbn, navController: NavController) {
        Log.d("detail",isbnCode)
        Column {
            InfoForIsbnComponent(bookItemIsbn)
            Divider()
            TitleTextField()
//        todo : image
            InfoDetailLine(title = "판매가")
            InfoDetailLine(title = "상세정보")
            Button(onClick = {  }) {
                Text("확인")
            }
        }
    }

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