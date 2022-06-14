package com.dynast.compose.ui.components.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.MainViewModel
import com.dynast.compose.extension.ContentType
import com.dynast.compose.extension.NavigationType
import com.dynast.compose.ui.components.nav.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NavigationWrapperUI(
    navHostController: NavHostController,
    navigationType: NavigationType,
    contentType: ContentType,
    viewModel: MainViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val bottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val loginBottomState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val loginState by viewModel.loginState.collectAsState()

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        NavDrawer(
            navController = navHostController,
            navigationType = navigationType,
            contentType = contentType,
            onBottomItemClick = { item ->
                when (item) {
                    BottomItems.MyClass -> {
                        if (loginState) {
                            navHostController.navigation(item)
                        } else scope.launch {
                            loginBottomState.show()
                        }
                    }
                    BottomItems.More -> scope.launch { snackBarHostState.showSnackbar(message = item.title) }
                    else -> navHostController.navigation(item)
                }
            }
        )
    } else {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        ModalNavigationDrawer(
            gesturesEnabled = navigationType == NavigationType.NAVIGATION_RAIL,
            drawerContent = {
                Drawer(
                    selectedDestination = currentRoute,
                    navigationType = navigationType
                ) { item ->
                    scope.launch {
                        drawerState.close()
                        item?.let { item ->
                            when (item) {
                                BottomItems.MyClass -> {
                                    if (loginState) {
                                        navHostController.navigation(item)
                                    } else {
                                        loginBottomState.show()
                                    }
                                }
                                BottomItems.More -> scope.launch { snackBarHostState.showSnackbar(message = item.title) }
                                else -> navHostController.navigation(item)
                            }
                        }
                    }
                }
            },
            drawerState = drawerState
        ) {
            AppContent(
                navHostController = navHostController,
                navigationType = navigationType,
                contentType = contentType,
                onDrawerClicked = { scope.launch { drawerState.open() } },
                onBottomItemClick = { item ->
                    when (item) {
                        BottomItems.MyClass -> {
                            if (loginState) {
                                navHostController.navigation(item)
                            } else scope.launch {
                                loginBottomState.show()
                            }
                        }
                        BottomItems.More -> scope.launch { bottomState.show() }
                        else -> navHostController.navigation(item)
                    }
                }
            )
        }
        BottomSheet(state = bottomState)
    }
    LoginSheet(state = loginBottomState)
    SnackbarHost(
        snackBarHostState,
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(align = Alignment.Bottom)
            .padding(bottom = if (navigationType == NavigationType.BOTTOM_NAVIGATION) 80.dp else 0.dp)
    )
}

@Composable
fun AppContent(
    navHostController: NavHostController,
    navigationType: NavigationType,
    contentType: ContentType,
    onDrawerClicked: () -> Unit = {},
    onBottomItemClick: (BottomItems) -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
            NavRail(
                navController = navHostController,
                items = railItem,
                onClick = { item -> onBottomItemClick(item) },
                headerClick = onDrawerClicked
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            NavGraph(
                navController = navHostController,
                modifier = Modifier.weight(1f),
                contentType = contentType,
                startDestination = BottomItems.Free.route
            )
            AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION) {
                NavBar(navController = navHostController, items = items,
                    onClick = { item -> onBottomItemClick(item) })
            }
        }
    }
}

fun NavController.navigation(item: BottomItems) {
    navigate(item.route) {
        graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) { saveState = true }
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
        }
    ) {}
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier
            .wrapContentHeight(Alignment.Bottom)
            .padding(bottom = 80.dp)
    )
}