package com.dynast.compose.free

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynast.compose.ui.components.free.DropDownScreen
import com.dynast.compose.ui.theme.ComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Vertical))
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                DropDownScreen(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(10) {
                Card(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            modifier = Modifier.size(64.dp),
                            imageVector = Icons.Filled.Image, contentDescription = null
                        )
                        Column {
                            Text(text = "[2020] 공인중개사 중개사법령 및 중개실무 핵심이론 단과반(장석태)")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "장석태 | 총 5강")
                        }
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        border = BorderStroke(Dp.Hairline, color = Color.Black),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {}) {
                        Text(text = "수강 신청", color = Color(0xff333333))
                    }
                }
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