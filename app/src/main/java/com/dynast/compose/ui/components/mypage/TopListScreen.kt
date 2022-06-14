package com.dynast.compose.ui.components.mypage

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynast.compose.ui.theme.ComposeTheme

@Composable
fun TopList(
    onClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            var notifyCnt by remember { mutableStateOf(1) }
            var notifyCnt2 by remember { mutableStateOf(10) }
            val notifyCnt3 by remember { mutableStateOf(100) }
            val modifier = Modifier
                .weight(1F)
                .padding()
            TopListItem(modifier = modifier, title = "공지사항", image = Icons.Filled.Favorite, notify = notifyCnt, onClicked = {
                notifyCnt = 0
            })
            TopListItem(modifier = modifier, title = "업데이트 강좌", image = Icons.Filled.Edit, notify = notifyCnt2, onClicked = {
                notifyCnt2 = 0
            }, column = Modifier.padding(top = 16.dp, bottom = 16.dp))
            TopListItem(modifier = modifier, title = "출석현황", image = Icons.Filled.Person, notify = 0, onClicked = onClicked)
            TopListItem(modifier = modifier, title = "이용방법", image = Icons.Filled.Notifications, notify = notifyCnt3, onClicked = onClicked)
        }
        Divider(thickness = Dp.Hairline)
    }
}

@Composable
fun TopListItem(
    modifier: Modifier,
    title: String,
    image: ImageVector,
    column: Modifier = Modifier.padding(16.dp),
    notify: Int? = null,
    onClicked: (String) -> Unit
) {
    Box(
        modifier = modifier.clickable { onClicked(title) }, contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = column
        ) {
            BadgedBox(
                modifier = Modifier.padding(8.dp),
                badge = {
                    notify?.apply {
                        when {
                            notify > 99 -> {
                                Badge { Text(text = "99+") }
                            }
                            notify in 1..99 -> {
                                Badge { Text(text = notify.toString()) }
                            }
                            else -> {
                                Badge()
                            }
                        }
                    }
                },
            ) {
                Image(painter = rememberVectorPainter(image = image), contentDescription = title)
            }
//            Box(modifier = Modifier.padding(8.dp)) {
//                Image(painter = rememberVectorPainter(image = image), contentDescription = title)
//            }
            Text(text = title)
        }
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
fun TopListPreview() {
    ComposeTheme {
        TopList(onClicked = {})
    }
}