package com.dynast.compose.ui.components.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dynast.compose.MainViewModel
import com.dynast.compose.extension.ContentType
import com.dynast.compose.ui.components.main.BottomItems
import com.dynast.compose.ui.favorite.FavoriteScreen
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
                FreeScreen(modifier = modifier, paging = viewModel.getPagingData)
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