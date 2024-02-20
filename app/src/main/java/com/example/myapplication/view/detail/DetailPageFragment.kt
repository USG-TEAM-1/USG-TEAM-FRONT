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


object DetailPageFragment {

    @Composable
    fun view() = Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        TopNavigationComponent.view()
        BookDetailScreen(viewModel = BookDetailViewModel(1))
    }

    @Composable
    fun BookDetailScreen(viewModel: BookDetailViewModel) {
        val bookItem by viewModel.bookDetail.observeAsState()

        bookItem?.let {
            BookDetailComponent.view(it)
        }
    }
}