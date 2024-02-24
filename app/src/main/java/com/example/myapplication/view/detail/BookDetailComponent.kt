package com.example.myapplication.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.data.BookItem
import com.example.myapplication.view.component.IconButtonComponent.IconButton

object BookDetailComponent {

    @Composable
    fun view(bookItem: BookItem) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PostTitleText(bookItem.bookPostName)
                MenuButton()
            }
            bookItem.nickname?.let { text(text = it) }

            Row {
                bookItem.imageUrls.forEach { imageUrl ->
                    BookImage(imageUrl)
                }
            }
            
            text(bookItem.bookName)
            Row{
                text(text = bookItem.bookPrice.toString())
                text(bookItem.bookRealPrice.toString())
            }
            Row{
                text(text = "저자")
                text(bookItem.author)
            }
            Row{
                text(text = "출판사")
                text(bookItem.publisher)
            }
            text(text = bookItem.bookComment)
        }

    }

    @Composable
    private fun PostTitleText(text: String) = Text(
        text = text,
        fontSize = 30.sp
    )

    @Composable
    private fun MenuButton() = IconButton(
        onClick = {},
        modifier = Modifier.padding(end = 19.dp)
        ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            tint = Color.Unspecified, // 로고 색 표현
            contentDescription = "메뉴 버튼"
        )
    }

    @Composable
    private fun text(text: String) = Text(
        text = text,
        fontSize = 30.sp
    )

    @Composable
    private fun BookImage(imageUrl: String) {
        val painter = rememberImagePainter(imageUrl)

        Image(
            painter = painter,
            contentDescription = "이미지 설명",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            contentScale = ContentScale.Crop
        )
    }
}