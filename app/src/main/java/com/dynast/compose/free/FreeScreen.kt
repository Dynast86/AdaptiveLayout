package com.dynast.compose.free

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.compose.ui.components.free.CourseCard
import com.dynast.compose.ui.components.free.DropDownScreen
import com.dynast.compose.ui.theme.ComposeTheme

@Composable
fun FreeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.background(Color(0xFFE5E5E5))
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Vertical))
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                DropDownScreen(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(10) {
                CourseCard()
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
        FreeScreen()
    }
}