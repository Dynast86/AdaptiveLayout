package com.dynast.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dynast.compose.ui.components.main.MainScreen
import com.dynast.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class BottomItems(val image: ImageVector, val title: String, val route: String) {
    object MyClass : BottomItems(Icons.Filled.Favorite, "나의강의실", "MyClass")
    object Solve : BottomItems(Icons.Filled.Email, "딱풀", "Solve")
    object Free : BottomItems(Icons.Filled.Place, "무료특강", "Free")
    object MyPage : BottomItems(Icons.Filled.Notifications, "마이페이지", "MyPage")
    object More : BottomItems(Icons.Filled.Favorite, "더보기", "More")
}

val items = listOf(
    BottomItems.MyClass,
    BottomItems.Solve,
    BottomItems.Free,
    BottomItems.MyPage,
    BottomItems.More,
)

val railItem = listOf(
    BottomItems.MyClass,
    BottomItems.Solve,
    BottomItems.Free,
    BottomItems.MyPage,
)


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(viewModel = viewModel, windowSizeClass = calculateWindowSizeClass(activity = this))
                }
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        NavigationBar {
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