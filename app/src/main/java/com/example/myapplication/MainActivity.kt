package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.BookItemIsbn
import com.example.myapplication.view.auth.LoginPageFragment
import com.example.myapplication.view.auth.JoinPageFragment
import com.example.myapplication.view.auth.TokenManager
import com.example.myapplication.view.main.MainPageFragment
import com.example.myapplication.view.register.RegisterInfoInputDetail
import com.example.myapplication.view.register.RegisterInfoInputForIsbn
import com.example.myapplication.view.register.RegisterInfoInputForManually
import com.example.myapplication.view.register.SelectInfoInputFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent 내에서 @Composable 어노테이션 사용
        setContent {
            MainContent()
        }
    }
}

class MainViewModel() : ViewModel() {
    val bookItemIsbn = MutableLiveData<BookItemIsbn>()
    var isbnCode: String = ""
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    val context = LocalContext.current
    TokenManager.initialize(context)
    val token = remember { TokenManager.getToken() }

    val mainViewModel: MainViewModel = viewModel()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = if (token != null) "main" else "login") {
            composable("login") {
                LoginPageFragment.view(navController)
            }
            composable("join") {
                JoinPageFragment.view(navController)
            }
            composable("main") {
                MainPageFragment.view(navController)
            }
            composable("selectInfoInput") {
                SelectInfoInputFragment.view(navController)
            }
            composable("registerInfoInputForIsbn") {
                RegisterInfoInputForIsbn.view(navController, mainViewModel)
            }
            composable("registerInfoInputForManually") {
                RegisterInfoInputForManually.view(navController, mainViewModel)
            }
            composable("registerInfoInputDetail") {
                mainViewModel.bookItemIsbn.value?.let { bookItemIsbn ->
                    RegisterInfoInputDetail.view(mainViewModel.isbnCode, bookItemIsbn, navController)
                }
            }
        }
    }
}
