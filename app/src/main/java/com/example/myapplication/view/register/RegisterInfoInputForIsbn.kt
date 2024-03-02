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
import com.example.myapplication.api.book.RetrofitIsbnObj.bookApi
import androidx.compose.runtime.rememberCoroutineScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.RegisterViewModel
import com.example.myapplication.data.BookItemIsbn
import kotlinx.coroutines.launch


object RegisterInfoInputForIsbn {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun view(navController: NavHostController, registerViewModel: RegisterViewModel) {
        var isbn by remember { mutableStateOf(TextFieldValue("")) }
        val coroutineScope = rememberCoroutineScope()

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
            Button(onClick = {
                coroutineScope.launch {
                    val bookItemIsbn = getInfoForIsbn(isbn.text)
                    if (bookItemIsbn != null) {
                        // BookItemIsbn 객체를 ViewModel의 bookItemIsbn 속성에 저장합니다.
                        registerViewModel.bookItemIsbn.value = bookItemIsbn
                        registerViewModel.isbnCode = isbn.text
                        navController.navigate("registerInfoInputDetail")
                    }
                }
            }) {
                Text("확인")
            }
        }
    }

    private suspend fun getInfoForIsbn(isbnCode: String): BookItemIsbn? {
        val key = BuildConfig.API_KEY

        return try {
            val response = bookApi.getBookIsbn(key, true, "isbn", isbnCode)
            val bookItems = response.result.bookItems
            bookItems.firstOrNull()
        } catch (e: Exception) {
            println("API 요청 중 오류 발생: ${e.message}")
            null
        }
    }


}