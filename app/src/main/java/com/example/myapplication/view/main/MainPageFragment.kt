package com.example.myapplication.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object MainPageFragment{
    @Composable
    fun view() {
        Box {
            Column {
                TopSectionComponent.view()
                BookListComponent.view()
            }
            SellBookButtonComponent.SellBookButton(
                modifier = Modifier
                .width(137.dp)
                .height(56.dp)
                .align(Alignment.BottomEnd),
            )
        }
    }
}
