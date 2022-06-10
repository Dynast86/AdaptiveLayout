package com.dynast.compose.ui.components.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dynast.compose.R
import com.dynast.compose.extension.ContentType
import com.dynast.compose.extension.NavigationType
import com.dynast.compose.ui.components.main.AppContent
import com.dynast.compose.ui.components.main.BottomItems
import com.dynast.compose.ui.components.main.railItem
import com.dynast.compose.ui.theme.ComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    navController: NavHostController,
    navigationType: NavigationType,
    contentType: ContentType,
    onBottomItemClick: (BottomItems) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    PermanentNavigationDrawer(drawerContent = {
        Drawer(
            selectedDestination = currentRoute,
            navigationType = navigationType,
        ) { item ->
            item?.apply {
                onBottomItemClick(item)
            }
        }
    }) {
        AppContent(
            navHostController = navController,
            navigationType = navigationType,
            contentType = contentType,
            onDrawerClicked = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    selectedDestination: String?,
    navigationType: NavigationType,
    onDrawerClicked: (BottomItems?) -> Unit = {}
) {
    Column(
        modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            if (navigationType != NavigationType.PERMANENT_NAVIGATION_DRAWER) {
                IconButton(onClick = { onDrawerClicked(null) }) {
                    Icon(
                        imageVector = Icons.Default.MenuOpen,
                        contentDescription = null
                    )
                }
            }
        }

        railItem.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.title, modifier = Modifier.padding(horizontal = 16.dp)) },
                selected = selectedDestination == item.route,
                onClick = { onDrawerClicked(item) },
                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    ComposeTheme {
        Drawer(
            selectedDestination = railItem[0].route,
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
        )
    }
}