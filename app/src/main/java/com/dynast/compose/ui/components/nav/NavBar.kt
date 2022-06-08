package com.dynast.compose.ui.components.nav

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.BottomItems
import com.dynast.compose.MainViewModel
import com.dynast.compose.items
import com.dynast.compose.ui.theme.ComposeTheme

@Composable
fun NavBar(
    navController: NavController,
    items: List<BottomItems>,
    onClick: (BottomItems) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    NavigationBar(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
        )
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val selected = if (currentRoute == item.route) {
                viewModel.setTopBarTitle(item.title)
                true
            } else false
            NavigationBarItem(
                selected = selected,
                onClick = { onClick(item) },
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
//            val selected = if (currentRoute == item.route) {
//                viewModel.setTopBarTitle(item.title)
//                true
//            } else false
//            BottomNavigationItem(
//                selected = selected,
//                onClick = {
//                    when (item) {
//                        BottomItems.More -> onClick(item)
//                        BottomItems.MyClass -> {
//                            if (loginState.value) {
//                                navController.navigation(item)
//                            } else onClick(item)
//                        }
//                        else -> navController.navigation(item)
//                    }
//                },
//                icon = { Icon(imageVector = item.image, contentDescription = item.title) },
//                label = { Text(text = item.title) }
//            )
//        }
//    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun NavBarPreview() {
    ComposeTheme {
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
//    BottomNavigation {
//        var selectedItem by remember { mutableStateOf(0) }
//
//        items.forEachIndexed { index, s ->
//            BottomNavigationItem(
//                selected = selectedItem == index,
//                onClick = { selectedItem = index },
//                icon = { Icon(imageVector = s.image, contentDescription = null) },
//                label = { Text(text = s.title) }
//            )
//        }
//    }
    }
}