package com.dynast.compose.ui.components.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dynast.compose.BottomItems
import com.dynast.compose.MainViewModel
import com.dynast.compose.items
import com.dynast.compose.railItem
import com.dynast.compose.ui.components.TopAppBarScreen
import com.dynast.compose.ui.components.nav.NavBar
import com.dynast.compose.ui.components.nav.NavGraph
import com.dynast.compose.ui.components.nav.NavRail
import com.dynast.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    windowSizeClass: WindowSizeClass
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val bottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val loginBottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val loginState = viewModel.loginState.collectAsState()

    Scaffold(
        topBar = { TopAppBarScreen() },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                NavBar(navController = navController, items = items, onClick = { item ->
                    when (item) {
                        BottomItems.More -> scope.launch {
//                            bottomState.show()
                            snackBarHostState.showSnackbar(message = item.title)
                        }
                        BottomItems.MyClass -> {
                            if (loginState.value) {
                                navController.navigation(item)
                            } else scope.launch {
                                loginBottomState.show()
                            }
                        }
                        else -> navController.navigation(item)
                    }
                }, viewModel = viewModel)
            }
        }) { paddingContent ->
        var padding: Dp = paddingContent.calculateStartPadding(LayoutDirection.Ltr)
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                NavRail(navController = navController, items = railItem, onClick = { item ->
                    when (item) {
                        BottomItems.More -> scope.launch {
//                            bottomState.show()
                            snackBarHostState.showSnackbar(message = item.title)
                        }
                        BottomItems.MyClass -> {
                            if (loginState.value) {
                                navController.navigation(item)
                            } else scope.launch {
                                loginBottomState.show()
                            }
                        }
                        else -> navController.navigation(item)
                    }
                }) {
                    padding = it
                }
            }
        }
        NavGraph(
            navController = navController, modifier = Modifier
                .windowInsetsPadding(
                    WindowInsets(
                        left = padding,
                        top = paddingContent.calculateTopPadding(),
                        bottom = paddingContent.calculateBottomPadding()
                    )
                )
                .fillMaxSize()
        )
    }
    LoginSheet(state = loginBottomState)
    BottomSheet(state = bottomState)
}

fun NavController.navigation(item: BottomItems) {
    navigate(item.route) {
        graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
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

@OptIn(ExperimentalMaterialApi::class)
@Preview(
    device = Devices.AUTOMOTIVE_1024p,
    name = "Landscape"
)
@Preview(
    name = "Portrait"
)
@Composable
fun BottomSheetPreview() {
    val bottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    ComposeTheme {
        BottomSheet(state = bottomState)
    }
}

val bottomShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)