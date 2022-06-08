package com.dynast.compose.ui.free

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.dynast.compose.ui.components.free.ChipsScreen
import com.dynast.compose.ui.components.free.CourseCard
import com.dynast.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun FreeScreen(
    modifier: Modifier = Modifier,
    paging: Flow<PagingData<CourseCardData>>,
    windowSizeClass: WindowSizeClass? = null
) {
    val listState = rememberLazyListState()
    val page = paging.collectAsLazyPagingItems()
    val maxDp = remember { mutableStateOf(Dp.Unspecified) }
    windowSizeClass?.apply {
        if (widthSizeClass != WindowWidthSizeClass.Compact) maxDp.value = 330.dp
    }

    Box(
        modifier = Modifier
            .background(Color(0xFFE5E5E5))
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Vertical))
            .widthIn(max = maxDp.value)
    ) {
        Column {
//            DropDownScreen(modifier = Modifier.fillMaxWidth())
            ChipsScreen(modifier = Modifier.fillMaxWidth())
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            ) {
                items(items = page) { value -> CourseCard(item = value!!) }
            }
        }

//        TopAppBar()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
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
        FreeScreen(paging = flowOf(PagingData.from(previewState())))
    }
}

fun previewState(): List<CourseCardData> {
    val items = mutableListOf<CourseCardData>()
    repeat(50) {
        items.add(
            CourseCardData(title = "Preview $it", content = "테스트 | 총 ${it}강")
        )
    }
    return items.toList()
}