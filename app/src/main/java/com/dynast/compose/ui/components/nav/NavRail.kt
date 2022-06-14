package com.dynast.compose.ui.components.nav

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.ui.components.main.BottomItems
import com.dynast.compose.ui.components.main.railItem
import com.dynast.compose.ui.theme.ComposeTheme

@Composable
fun NavRail(
    navController: NavController,
    items: List<BottomItems>,
    onClick: (BottomItems) -> Unit,
    headerClick: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationRail(
        header = {
            Spacer(modifier = Modifier.height(56.dp))
            IconButton(
                onClick = headerClick
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Localized description")
            }
//            Spacer(modifier = Modifier.height(40.dp))
        }
    ) {
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationRailItem(
                selected = currentRoute == item.route,
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
fun NavRailPreview(onDrawerClicked: () -> Unit = {}) {
    ComposeTheme {
        NavigationRail(
            header = {
                Spacer(modifier = Modifier.height(56.dp))
                Image(
                    modifier = Modifier.clickable { },
                    imageVector = Icons.Filled.Menu, contentDescription = null
                )
                Spacer(modifier = Modifier.height(40.dp))
            },
            modifier = Modifier.fillMaxHeight()
        ) {
            railItem.forEach { item ->
                NavigationRailItem(
                    selected = false,
                    onClick = onDrawerClicked,
                    icon = { Icon(imageVector = item.image, contentDescription = item.title) },
                    label = { Text(text = item.title) })
            }
        }
    }
}