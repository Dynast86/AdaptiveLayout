package com.dynast.compose.ui.components.nav

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynast.compose.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    PermanentNavigationDrawer(drawerContent = { Drawer(modifier = modifier) }, modifier = modifier) {

    }
}

@Composable
fun Drawer(modifier: Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 48.dp)
    ) {
        items.forEach { item ->
            Spacer(Modifier.height(24.dp))
            Text(text = item.title, color = Color.Black)
        }
    }
}