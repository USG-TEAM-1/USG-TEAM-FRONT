package com.example.myapplication.view.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.view.component.TopNavigationComponent

object DetailPageFragment {
    @Composable
    fun view() = Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        TopNavigationComponent.view()
        BookDetailComponent.view()
    }
}