package com.example.myapplication.view.detail

import BookDetailViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.myapplication.view.component.TopNavigationComponent
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.ModifyViewModel
import com.example.myapplication.data.BookItem


object DetailPageFragment {

    @Composable
    fun view(
        navController: NavHostController,
        modifyViewModel: ModifyViewModel,
        detailViewModel: BookDetailViewModel
    ) = Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        TopNavigationComponent.view()
        BookDetailScreen(detailViewModel, modifyViewModel, navController)
    }

    @Composable
    fun BookDetailScreen(viewModel: BookDetailViewModel, modifyViewModel: ModifyViewModel, navController: NavController) {
        BookDetailComponent.view(viewModel, modifyViewModel, navController)
    }
}