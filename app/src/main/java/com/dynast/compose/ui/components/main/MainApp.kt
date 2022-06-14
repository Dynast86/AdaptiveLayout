package com.dynast.compose.ui.components.main

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dynast.compose.HomeUIState
import com.dynast.compose.extension.ContentType
import com.dynast.compose.extension.DevicePosture
import com.dynast.compose.extension.NavigationType

sealed class BottomItems(val image: ImageVector, val title: String, val route: String) {
    object MyClass : BottomItems(Icons.Filled.Favorite, "나의강의실", "MyClass")
    object Solve : BottomItems(Icons.Filled.Email, "딱풀", "Solve")
    object Free : BottomItems(Icons.Filled.Place, "무료특강", "Free")
    object MyPage : BottomItems(Icons.Filled.Notifications, "마이페이지", "MyPage")
    object More : BottomItems(Icons.Filled.Favorite, "더보기", "More")
}

val items = listOf(
    BottomItems.MyClass, BottomItems.Solve, BottomItems.Free, BottomItems.MyPage, BottomItems.More,
)

val railItem = listOf(
    BottomItems.MyClass, BottomItems.Solve, BottomItems.Free, BottomItems.MyPage,
)

@Composable
fun MainScreen(
    uiState: HomeUIState,
    windowSizeClass: WindowSizeClass,
    devicePosture: DevicePosture,
) {
    val navigationType: NavigationType
    val contentType: ContentType

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        ORIENTATION_PORTRAIT -> {
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    navigationType = NavigationType.BOTTOM_NAVIGATION
                    contentType = ContentType.LIST_ONLY
                }
                WindowWidthSizeClass.Medium -> {
                    navigationType = NavigationType.NAVIGATION_RAIL
                    contentType = if (devicePosture == DevicePosture.NormalPosture) {
                        ContentType.LIST_AND_DETAIL
                    } else {
                        ContentType.LIST_ONLY
                    }
                }
                WindowWidthSizeClass.Expanded -> {
                    navigationType = if (devicePosture is DevicePosture.BookPosture) {
                        NavigationType.NAVIGATION_RAIL
                    } else {
                        NavigationType.PERMANENT_NAVIGATION_DRAWER
                    }
                    contentType = ContentType.LIST_AND_DETAIL
                }
                else -> {
                    navigationType = NavigationType.BOTTOM_NAVIGATION
                    contentType = ContentType.LIST_ONLY
                }
            }
        }
        else -> {
            when (windowSizeClass.heightSizeClass) {
                WindowHeightSizeClass.Compact -> {
                    navigationType = NavigationType.NAVIGATION_RAIL
                    contentType = ContentType.LIST_ONLY
                }
                else -> {
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
                        navigationType = NavigationType.NAVIGATION_RAIL
                        contentType = ContentType.LIST_AND_DETAIL
                    } else {
                        navigationType = NavigationType.NAVIGATION_RAIL
                        contentType = ContentType.LIST_AND_DETAIL
                    }
                }
            }
        }
    }

    val navController = rememberNavController()
    NavigationWrapperUI(
        navHostController = navController,
        navigationType = navigationType,
        contentType = contentType,
    )
}

//@OptIn(ExperimentalMaterialApi::class)
//@Preview(
//    device = Devices.AUTOMOTIVE_1024p,
//    name = "Landscape"
//)
//@Preview(
//    name = "Portrait"
//)
//@Composable
//fun BottomSheetPreview() {
//    val bottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
//    ComposeTheme {
//        BottomSheet(state = bottomState)
//    }
//}

val bottomShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)