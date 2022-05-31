package com.dynast.compose.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dynast.compose.MainViewModel
import com.dynast.compose.ui.components.mypage.LoginScreen
import com.dynast.compose.ui.components.mypage.TopList

@Composable
fun MyPageScreen(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column {
        LoginScreen()
        Divider(thickness = 0.5.dp)
        TopList(onClicked = {
            println("TopList onClick :$it")
        })
    }
}