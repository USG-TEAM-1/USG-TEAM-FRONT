package com.example.myapplication.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
