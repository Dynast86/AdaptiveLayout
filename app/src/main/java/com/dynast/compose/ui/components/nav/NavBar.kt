package com.dynast.compose.ui.components.nav

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.BottomItems
import com.dynast.compose.MainViewModel
import com.dynast.compose.items

@Composable
fun NavBar(
    navController: NavController,
    items: List<BottomItems>,
    onClick: (BottomItems) -> Unit,
    viewModel: MainViewModel
) {
    NavigationBar(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
        )
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (item == BottomItems.More
                        || item == BottomItems.MyClass
                    ) {
                        onClick(item)
                    } else {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.image, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )
        }
    }
//    BottomNavigation(
//        modifier = Modifier.windowInsetsPadding(
//            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
//        )
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//        items.forEach { item ->
//            BottomNavigationItem(
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//
//                        navController.graph.startDestinationRoute?.let { screen_route ->
//                            popUpTo(screen_route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                icon = { Icon(imageVector = item.image, contentDescription = item.title) },
//                label = { Text(text = item.title) }
//            )
//        }
//    }
}

@Preview
@Composable
fun NavBarPreview() {
    NavigationBar(
        tonalElevation = 12.dp
    ) {
        var selectedItem by remember { mutableStateOf(0) }

        items.forEachIndexed { index, s ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { Icon(imageVector = s.image, contentDescription = null) },
                label = { Text(text = s.title) }
            )
        }
    }
}