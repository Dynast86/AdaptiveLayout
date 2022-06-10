package com.dynast.compose.ui.components.nav

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dynast.compose.MainViewModel
import com.dynast.compose.extension.ContentType
import com.dynast.compose.ui.components.free.ChipsScreen
import com.dynast.compose.ui.components.free.CourseCard
import com.dynast.compose.ui.components.main.BottomItems
import com.dynast.compose.ui.favorite.FavoriteScreen
import com.dynast.compose.ui.free.CourseCardData
import com.dynast.compose.ui.free.FreeDetailContent
import com.dynast.compose.ui.free.FreeScreen
import com.dynast.compose.ui.myclass.MyClassScreen
import com.dynast.compose.ui.mypage.MyPageScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    contentType: ContentType,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(BottomItems.MyClass.route) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MyClassScreen(mainViewModel)
        }
        composable(BottomItems.Solve.route) {
            val parentViewModel = hiltViewModel<MainViewModel>(it)
            FavoriteScreen(parentViewModel, modifier = modifier)
        }
        composable(BottomItems.Free.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            if (contentType == ContentType.LIST_AND_DETAIL) {
                Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    FreeScreen(modifier = modifier, paging = viewModel.getPagingData)
                    FreeDetailContent(modifier = modifier)
                }
            } else {
                FreeScreen(modifier = modifier, paging = viewModel.getPagingData)
            }
        }
        composable(BottomItems.MyPage.route) {
            val parentViewModel = hiltViewModel<MainViewModel>(it)
            MyPageScreen(parentViewModel)
        }
        composable(BottomItems.More.route) {

        }
    }
}

@Preview(showBackground = true, widthDp = 1024)
@Composable
fun FreeListAndContentPreview() {
    val page = mutableListOf<CourseCardData>()
    repeat(50) {
        page.add(
            CourseCardData(title = "Preview $it", content = "테스트 | 총 ${it}강")
        )
    }
    val listState = rememberLazyListState()

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier) {
                ChipsScreen(modifier = Modifier.fillMaxWidth())
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(items = page) { value -> CourseCard(item = value) }
                }
            }
        }
        FreeDetailContent(modifier = Modifier.weight(1f))
    }
}