package com.example.myapplication.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

object MainPageFragment{
    @Composable
    fun view() {
        Column {
            TopSectionComponent.view()
            BookListComponent.view()
        }
    }
}
