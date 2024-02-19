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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.dummyData
import com.example.myapplication.view.component.IconButtonComponent.IconButton

object BookDetailComponent {
    @Composable
    fun view() = Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PostTitleText()
            MenuButton()
        }
        UserComponent()
        
    }

    @Composable
    private fun PostTitleText() = Text(
        text = dummyData[0].postTitle,
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
    private fun UserComponent() = Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        UserImage()
        UserNicknameText()
    }

    @Composable
    private fun UserImage() = Image(
        painter = painterResource(id = R.drawable.ic_main_mypage),
        contentDescription = "이미지 설명",
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
    )

    @Composable
    private fun UserNicknameText() = Text(
        text = dummyData[0].memberNickname,
        fontSize = 20.sp
    )

    @Composable
    private fun BookTitleText() = Text(
        text = dummyData[0].title,
        fontSize = 25.sp
    )

    @Composable
    private fun SalePriceText() = Text(
        text = dummyData[0].salePrice,
        fontSize = 25.sp
    )

    @Composable
    private fun OriginalPriceText() = Text(
        text = dummyData[0].originalPrice,
        fontSize = 20.sp,
        color = Color.Gray
    )

    @Composable
    private fun ContentText() = Text(
        text = dummyData[0].originalPrice,
        fontSize = 20.sp
    )
}