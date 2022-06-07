package com.dynast.compose.ui.free

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.dynast.compose.ui.components.free.CourseCard
import com.dynast.compose.ui.components.free.DropDownScreen
import com.dynast.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FreeScreen(
    modifier: Modifier = Modifier,
    uiState: StateFlow<FreeUiState>,
//    paging: Flow<PagingData<CourseCardData>>? = null
) {
    val listState = rememberLazyListState()
    val freeUiState = uiState.collectAsState()
//    val page = paging?.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .background(Color(0xFFE5E5E5))
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Vertical))
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxSize()
    ) {
        LazyColumn(state = listState) {
            item {
                DropDownScreen(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(freeUiState.value.data) {
                CourseCard(item = it)
            }
        }
//        TopAppBar()
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
fun FreeScreenPreview() {
    val state = MutableStateFlow(
        FreeUiState(data = previewState())
    )
    ComposeTheme {
        FreeScreen(uiState = state)
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