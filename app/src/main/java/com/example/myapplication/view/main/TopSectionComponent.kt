package com.example.myapplication.view.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

object TopSectionComponent {
    @Composable
    fun view () {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp, start = 19.dp, end = 19.dp)
            ) {
            ChattingListButton()
            AppNameText()
            MyPageButton()
        }
    }

    @Composable
    private fun ChattingListButton() = IconButton(
        onClick = {},
        modifier = Modifier.padding(start = 19.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_main_chatting_list),
            tint = Color.Unspecified, // 로고 색 표현
            contentDescription = "채팅 목록 버튼"
        )
    }

    @Composable
    private fun AppNameText() = Text(
        text = "책과 콩나무",
        fontSize = 30.sp
    )

    @Composable
    private fun MyPageButton() = IconButton(
        onClick = {},
        modifier = Modifier.padding(end = 19.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_main_mypage),
            tint = Color.Unspecified, // 로고 색 표현
            contentDescription = "마이페이지 버튼"
        )
    }

    @Composable
    private fun IconButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        content: @Composable () -> Unit
    ) {
//        val combinedModifier = Modifier
//            .clickable(
//                enabled = enabled,
//                interactionSource = interactionSource,
//                indication = null,
//                onClick = onClick
//            )
//            .then(modifier)
        content()
    }
}