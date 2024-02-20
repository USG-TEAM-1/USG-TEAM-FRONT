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
import androidx.navigation.NavHostController

object RegisterInfoInputForIsbn {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun view(navController: NavHostController) {
        var isbn by remember { mutableStateOf(TextFieldValue("")) }
        Column {
            Text("책 정보 등록")
            Row {
                Text("ISBN")
                TextField(
                    value = isbn,
                    onValueChange = { newText -> isbn = newText },
                    singleLine = true,
                )
            }
            Button(onClick = {  }) {
                Text("확인")
            }
        }
    }
}