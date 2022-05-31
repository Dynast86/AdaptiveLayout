package com.dynast.compose.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dynast.compose.MainViewModel
import com.dynast.compose.ui.components.mypage.CourseScreen
import com.dynast.compose.ui.components.mypage.LoginScreen
import com.dynast.compose.ui.components.mypage.TopList

@Composable
fun MyPageScreen(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column {
        LoginScreen()
        TopList(onClicked = {
            println("TopList onClick :$it")
        })
        CourseScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    val mainViewModel by lazy { MainViewModel() }
    MyPageScreen(mainViewModel)
}