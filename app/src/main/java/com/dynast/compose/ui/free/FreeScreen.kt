package com.dynast.compose.ui.free

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
) {
    val listState = rememberLazyListState()
    val page = paging.collectAsLazyPagingItems()

    Box(
        modifier = modifier
    ) {
        Column(modifier = modifier) {
//            DropDownScreen(modifier = Modifier.fillMaxWidth())
            ChipsScreen(modifier = Modifier.fillMaxWidth())
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                modifier = modifier.weight(1f)
            ) {
                items(items = page) { value -> CourseCard(item = value!!) }
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