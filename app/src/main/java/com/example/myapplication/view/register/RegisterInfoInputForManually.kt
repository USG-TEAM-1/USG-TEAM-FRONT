package com.example.myapplication.view.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
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

object RegisterInfoInputForManually {
    @Composable
    fun view(navController: NavController) {
        Column {
            Text(text = "책 정보 등록")
            EditText(column = "책 제목")
            EditText(column = "원가")
            EditText(column = "저자")
            EditText(column = "출판사")

            Button(onClick = {  }) {
                Text("확인")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun EditText(column: String) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        Row {
            Text(column)
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                singleLine = true,
            )
        }
    }
}