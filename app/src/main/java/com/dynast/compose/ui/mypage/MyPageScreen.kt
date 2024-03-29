package com.dynast.compose.ui.mypage

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dynast.compose.MainViewModel
import com.dynast.compose.ui.components.mypage.CourseScreen
import com.dynast.compose.ui.components.mypage.LoginScreen
import com.dynast.compose.ui.components.mypage.MyPageTabScreen
import com.dynast.compose.ui.components.mypage.TopList
import com.dynast.compose.ui.theme.ComposeTheme

@Composable
fun MyPageScreen(
    viewModel: MainViewModel = viewModel()
) {
    val scrollState = rememberScrollState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LoginScreen()
        }
        item {
            TopList(onClicked = {
                println("TopList onClick :$it")
            })
        }
        item { CourseScreen() }
        item { MyPageTabScreen() }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun MyPagePreview() {
    val viewModel: MainViewModel = hiltViewModel()
    ComposeTheme {
        MyPageScreen(viewModel)
    }
}