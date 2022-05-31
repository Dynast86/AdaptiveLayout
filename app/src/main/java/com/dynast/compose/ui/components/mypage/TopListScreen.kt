package com.dynast.compose.ui.components.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopList(
    onClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1F)
                    .padding()
                    .clickable { onClicked("공지사항") }, contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(modifier = Modifier.padding(8.dp), painter = rememberVectorPainter(image = Icons.Filled.Favorite), contentDescription = "공지사항")
                    Text(text = "공지사항")
                }
            }
            Box(
                modifier = Modifier
                    .weight(1F)
                    .clickable { onClicked("업데이트 강좌") },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Image(modifier = Modifier.padding(8.dp), painter = rememberVectorPainter(image = Icons.Filled.Favorite), contentDescription = "업데이트 강좌")
                    Text(text = "업데이트 강좌")
                }
            }
            Box(
                modifier = Modifier
                    .weight(1F)
                    .clickable { onClicked("출석현황") },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(modifier = Modifier.padding(8.dp), painter = rememberVectorPainter(image = Icons.Filled.Favorite), contentDescription = "출석현황")
                    Text(text = "출석현황")
                }
            }
            Box(
                modifier = Modifier
                    .weight(1F)
                    .clickable { onClicked("이용방법") },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(modifier = Modifier.padding(8.dp), painter = rememberVectorPainter(image = Icons.Filled.Favorite), contentDescription = "이용방법")
                    Text(text = "이용방법")
                }
            }
        }
        Divider(thickness = 0.5.dp)
    }
}

@Preview
@Composable
fun TopListPreview() {
    TopList(onClicked = {})
}