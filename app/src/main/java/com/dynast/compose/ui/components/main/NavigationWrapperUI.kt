package com.dynast.compose.ui.components.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.extension.ContentType
import com.dynast.compose.extension.NavigationType
import com.dynast.compose.ui.components.nav.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapperUI(
    navHostController: NavHostController,
    navigationType: NavigationType,
    contentType: ContentType,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        NavDrawer(
            navController = navHostController,
            navigationType = navigationType,
            contentType = contentType,
        )
    } else {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        ModalNavigationDrawer(
            drawerContent = {
                Drawer(
                    selectedDestination = currentRoute,
                    navigationType = navigationType
                ) { item ->
                    scope.launch {
                        drawerState.close()
                        item?.apply { navHostController.navigation(item) }
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
                onBottomItemClick = { item -> navHostController.navigation(item) }
            )
        }
    }
}


@Composable
fun AppContent(
    navHostController: NavHostController,
    navigationType: NavigationType,
    contentType: ContentType,
    onDrawerClicked: () -> Unit = {},
    onBottomItemClick: (BottomItems) -> Unit
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
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}