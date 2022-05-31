package com.dynast.compose.ui.components.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dynast.compose.BottomItems
import com.dynast.compose.items
import com.dynast.compose.railItem
import com.dynast.compose.ui.components.nav.NavBar
import com.dynast.compose.ui.components.nav.NavGraph
import com.dynast.compose.ui.components.nav.NavRail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val loginState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                NavBar(navController = navController, items = items, onClick = { item ->
                    when (item) {
                        BottomItems.MyClass -> scope.launch {
                            loginState.show()
                        }
                        BottomItems.More -> scope.launch {
                            bottomState.show()
                        }
                        else -> Unit
                    }
                })
            }
        }) { paddingContent ->
        var padding: Dp = paddingContent.calculateStartPadding(LayoutDirection.Ltr)
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                NavRail(navController = navController, items = railItem) {
                    padding = it
                }
            }
        }
        NavGraph(
            navController = navController, modifier = Modifier
                .windowInsetsPadding(WindowInsets(left = padding))
                .fillMaxSize()
        )
    }
    LoginSheet(state = loginState)
    BottomSheet(state = bottomState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(state: ModalBottomSheetState) {
//    var skipHalfExpanded by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            LazyColumn {
                items(3) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }
    ) {}
}