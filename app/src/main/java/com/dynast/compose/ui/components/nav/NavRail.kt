package com.dynast.compose.ui.components.nav

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.BottomItems
import com.dynast.compose.railItem
import com.dynast.compose.ui.theme.ComposeTheme


val railWidth = 80.dp

@Composable
fun NavRail(
    navController: NavController,
    modifier: Modifier = Modifier,
    items: List<BottomItems>,
    onClick: (BottomItems) -> Unit,
    content: @Composable (Dp) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationRail(modifier = modifier) {
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationRailItem(
                selected = currentRoute == item.route,
                onClick = { onClick(item) },
                icon = { Icon(imageVector = item.image, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )

        }
        content(railWidth)
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
fun NavRailPreview() {
    ComposeTheme {
        NavigationRail {
            railItem.forEach { item ->
                NavigationRailItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(imageVector = item.image, contentDescription = item.title) },
                    label = { Text(text = item.title) })
            }
        }
    }
}