package com.dynast.compose.ui.components.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopList(
    onClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            val modifier = Modifier
                .weight(1F)
                .padding()
            TopListItem(modifier = modifier, title = "공지사항", image = Icons.Filled.Favorite, onClicked = onClicked)
            TopListItem(modifier = modifier, title = "업데이트 강좌", image = Icons.Filled.Edit, onClicked = onClicked, column = Modifier.padding(top = 16.dp, bottom = 16.dp))
            TopListItem(modifier = modifier, title = "출석현황", image = Icons.Filled.Person, onClicked = onClicked)
            TopListItem(modifier = modifier, title = "이용방법", image = Icons.Filled.Notifications, onClicked = onClicked)
        }
        Divider(thickness = 0.5.dp)
    }
}

@Composable
fun TopListItem(
    modifier: Modifier,
    title: String,
    image: ImageVector,
    column: Modifier = Modifier.padding(16.dp),
    onClicked: (String) -> Unit
) {
    Box(
        modifier = modifier.clickable { onClicked(title) }, contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = column
        ) {
            Image(modifier = Modifier.padding(8.dp), painter = rememberVectorPainter(image = image), contentDescription = title)
            Text(text = title)
        }
    }
}

@Preview
@Composable
fun TopListPreview() {
    TopList(onClicked = {})
}