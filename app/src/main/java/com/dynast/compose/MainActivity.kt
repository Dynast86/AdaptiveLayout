package com.dynast.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.dynast.compose.extension.DevicePosture
import com.dynast.compose.extension.isBookPosture
import com.dynast.compose.extension.isSeparating
import com.dynast.compose.ui.components.main.MainScreen
import com.dynast.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devicePostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DevicePosture.BookPosture(foldingFeature.bounds)

                    isSeparating(foldingFeature) ->
                        DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

                    else -> DevicePosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )

        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val devicePosture = devicePostureFlow.collectAsState().value
                    val uiState = viewModel.uiState.collectAsState().value
                    val windowSizeClass = calculateWindowSizeClass(activity = this)

                    MainScreen(
                        uiState = uiState,
                        windowSizeClass = windowSizeClass.widthSizeClass,
                        devicePosture = devicePosture,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ComposeTheme {
        MainScreen(
            uiState = HomeUIState(courseCard = emptyList()),
            windowSizeClass = WindowWidthSizeClass.Compact,
            devicePosture = DevicePosture.NormalPosture,
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun AppPreviewTablet() {
    ComposeTheme {
        MainScreen(
            uiState = HomeUIState(courseCard = emptyList()),
            windowSizeClass = WindowWidthSizeClass.Medium,
            devicePosture = DevicePosture.NormalPosture,
        )
    }
}

@Preview(showBackground = true, widthDp = 1024)
@Composable
fun AppPreviewDesktop() {
    ComposeTheme {
        MainScreen(
            uiState = HomeUIState(courseCard = emptyList()),
            windowSizeClass = WindowWidthSizeClass.Expanded,
            devicePosture = DevicePosture.NormalPosture,
        )
    }
}

//@Preview(
//    uiMode = UI_MODE_NIGHT_NO,
//    showBackground = true,
//    name = "Light Mode"
//)
//@Preview(
//    uiMode = UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun DefaultPreview() {
//    ComposeTheme {
//        NavigationBar {
//            var selectedItem by remember { mutableStateOf(0) }
//
//            items.forEachIndexed { index, s ->
//                NavigationBarItem(
//                    selected = selectedItem == index,
//                    onClick = { selectedItem = index },
//                    icon = { Icon(imageVector = s.image, contentDescription = null) },
//                    label = { Text(text = s.title) }
//                )
//            }
//        }
//    }
//}