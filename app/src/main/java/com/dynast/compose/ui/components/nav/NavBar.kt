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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.MainViewModel
import com.dynast.compose.ui.components.main.BottomItems
import com.dynast.compose.ui.components.main.items
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
        NavigationBar(modifier = Modifier.fillMaxWidth()) {
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
}