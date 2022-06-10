package com.dynast.compose.ui.components.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dynast.compose.HomeUIState
import com.dynast.compose.MainViewModel
import com.dynast.compose.extension.ContentType
import com.dynast.compose.extension.DevicePosture
import com.dynast.compose.extension.NavigationType
import kotlinx.coroutines.launch

sealed class BottomItems(val image: ImageVector, val title: String, val route: String) {
    object MyClass : BottomItems(Icons.Filled.Favorite, "나의강의실", "MyClass")
    object Solve : BottomItems(Icons.Filled.Email, "딱풀", "Solve")
    object Free : BottomItems(Icons.Filled.Place, "무료특강", "Free")
    object MyPage : BottomItems(Icons.Filled.Notifications, "마이페이지", "MyPage")
    object More : BottomItems(Icons.Filled.Favorite, "더보기", "More")
}

val items = listOf(
    BottomItems.MyClass,
    BottomItems.Solve,
    BottomItems.Free,
    BottomItems.MyPage,
    BottomItems.More,
)

val railItem = listOf(
    BottomItems.MyClass,
    BottomItems.Solve,
    BottomItems.Free,
    BottomItems.MyPage,
)

@Composable
fun MainScreen(
    uiState: HomeUIState,
    windowSizeClass: WindowWidthSizeClass,
    devicePosture: DevicePosture,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navigationType: NavigationType
    val contentType: ContentType

    when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
            contentType = if (devicePosture != DevicePosture.NormalPosture) {
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

    val navController = rememberNavController()
    NavigationWrapperUI(
        navHostController = navController,
        navigationType = navigationType,
        contentType = contentType,
    )
//    val scope = rememberCoroutineScope()
//    val bottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val loginBottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val loginState by viewModel.loginState.collectAsState()
//    val drawerState = rememberDrawerState(DrawerValue.Closed)

//    LoginSheet(state = loginBottomState)
//    BottomSheet(state = bottomState)
//    NavigationDrawer(drawerState = drawerState)

//    Scaffold(
//        topBar = {
//            if (windowSizeClass == WindowWidthSizeClass.Compact) {
//                TopAppBarScreen(windowSizeClass)
//            }
//        },
//        snackbarHost = { SnackbarHost(snackBarHostState) },
//        bottomBar = {
//            if (windowSizeClass == WindowWidthSizeClass.Compact) {
//                NavBar(navController = navController, items = items, onClick = { item ->
//                    when (item) {
//                        BottomItems.More -> scope.launch {
////                            bottomState.show()
//                            snackBarHostState.showSnackbar(message = item.title)
//                        }
//                        BottomItems.MyClass -> {
//                            if (loginState) {
//                                navController.navigation(item)
//                            } else scope.launch {
//                                loginBottomState.show()
//                            }
//                        }
//                        else -> navController.navigation(item)
//                    }
//                }, viewModel = viewModel)
//            }
//        }) { paddingContent ->
//        var padding: Dp = paddingContent.calculateStartPadding(LayoutDirection.Ltr)
//        when (windowSizeClass) {
//            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
//                NavRail(navController = navController, items = railItem,
//                    headerClick = {
//                        println("!!!!!!")
//                        scope.launch { drawerState.open() }
//                    },
//                    onClick = { item ->
//                        when (item) {
//                            BottomItems.More -> scope.launch {
////                            bottomState.show()
//                                snackBarHostState.showSnackbar(message = item.title)
//                            }
//                            BottomItems.MyClass -> {
//                                if (loginState) {
//                                    navController.navigation(item)
//                                } else scope.launch {
//                                    loginBottomState.show()
//                                }
//                            }
//                            else -> navController.navigation(item)
//                        }
//                    }) {
//                    padding = it
//                }
//            }
//        }
//        NavGraph(
//            navController = navController, modifier = Modifier
//                .windowInsetsPadding(
//                    WindowInsets(
//                        left = padding,
//                        top = paddingContent.calculateTopPadding(),
//                        bottom = paddingContent.calculateBottomPadding()
//                    )
//                )
//                .fillMaxSize(), windowSizeClass = windowSizeClass
//        )
//    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(state: ModalBottomSheetState) {
//    var skipHalfExpanded by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = bottomShape,
        sheetContent = {
            LazyColumn {
                items(3) {
                    ListItem(
                        modifier = Modifier.clickable {
                            scope.launch {
                                snackBarHostState.showSnackbar(message = "Item $it")
                            }
                        },
                        text = { Text("Item $it") },
                        icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "Localized description") }
                    )
                }
            }
        },
        content = {}
    )
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier
            .wrapContentHeight(Alignment.Bottom)
            .padding(bottom = 80.dp)
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