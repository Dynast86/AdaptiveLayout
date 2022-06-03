package com.dynast.compose.ui.components.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dynast.compose.BottomItems
import com.dynast.compose.MainViewModel
import com.dynast.compose.favorite.FavoriteScreen
import com.dynast.compose.free.FreeScreen
import com.dynast.compose.myclass.MyClassScreen
import com.dynast.compose.mypage.MyPageScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = BottomItems.Free.route, modifier = modifier) {
        composable(BottomItems.MyClass.route) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MyClassScreen(mainViewModel)
        }
        composable(BottomItems.Solve.route) {
            val parentViewModel = hiltViewModel<MainViewModel>(it)
            FavoriteScreen(parentViewModel, modifier = modifier)
        }
        composable(BottomItems.Free.route) {
            FreeScreen(modifier = modifier)
        }
        composable(BottomItems.MyPage.route) {
            val parentViewModel = hiltViewModel<MainViewModel>(it)
            MyPageScreen(parentViewModel, modifier = modifier)
        }
        composable(BottomItems.More.route) {

        }
    }
}