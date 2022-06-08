package com.dynast.compose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dynast.compose.MainViewModel
import com.dynast.compose.extension.FunctionalityNotAvailablePopup
import com.dynast.compose.ui.theme.ComposeTheme

@Composable
fun TopAppBarScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    var functionalityNotAvailablePopupShown by remember { mutableStateOf(false) }
    if (functionalityNotAvailablePopupShown) {
        FunctionalityNotAvailablePopup { functionalityNotAvailablePopupShown = false }
    }

    val title = viewModel.title.observeAsState()

    SmallTopAppBar(
        title = { title.value?.let { Text(text = it) } },
        navigationIcon = {
            IconButton(
                onClick = { /* "Open nav drawer" */ }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Localized description")
            }
        },
        actions = {
//            Icon(
//                imageVector = Icons.Outlined.Search,
//                tint = MaterialTheme.colorScheme.onSurfaceVariant,
//                modifier = Modifier
//                    .clip(shape = RoundedCornerShape(8.dp))
//                    .clickable(onClick = { functionalityNotAvailablePopupShown = true })
//                    .padding(horizontal = 12.dp, vertical = 12.dp)
//                    .height(24.dp),
//                contentDescription = "Search"
//            )
            BadgedBox(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .clickable(onClick = { functionalityNotAvailablePopupShown = true })
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .height(24.dp),
                badge = {
                    Badge(
//                        content = { Text(text = "15") }
                    )
                },
                content = {
                    Image(imageVector = Icons.Outlined.Notifications, contentDescription = null)
                }
            )
        })
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
fun FreeScreenPreview() {
    ComposeTheme {
        val viewModel = hiltViewModel<MainViewModel>()
        TopAppBarScreen(viewModel = viewModel)
    }
}