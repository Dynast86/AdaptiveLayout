package com.dynast.compose.ui.components.free

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.compose.extension.UiAlertPopup
import com.dynast.compose.ui.free.CourseCardData

@Composable
fun CourseCard(
    item: CourseCardData
) {
    var alertPopupShown by remember { mutableStateOf(false) }
    if (alertPopupShown) {
        UiAlertPopup(
            title = "수강신청 완료",
            content = "에듀윌과 합격하세요!\n무료특강 수강 신청이 완료 되었습니다.",
            onDismiss = {
                alertPopupShown = false
            }) {
            alertPopupShown = false
        }
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp)) {
                Image(
                    alignment = Alignment.CenterStart,
                    imageVector = Icons.Filled.Image, contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(84.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(4.dp)),
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                        color = Color(0xFF222222)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.content, color = Color(0xFF888888),
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
            OutlinedButton(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                border = BorderStroke(1.dp, color = Color.Black),
                shape = RoundedCornerShape(4.dp),
                onClick = { alertPopupShown = true }) {
                Text(text = "수강 신청", color = Color(0xff333333), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    val item = CourseCardData(
        title = "[2020] 공인중개사 중개사법령 및 중개실무 핵심이론 단과반(장석태)",
        content = "장석태 | 총 5강"
    )
    CourseCard(item = item)
}