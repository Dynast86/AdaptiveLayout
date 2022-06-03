package com.dynast.compose.ui.components.free

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynast.compose.extension.UiAlertPopup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard() {
    var alertPopupShown by remember { mutableStateOf(false) }
    if (alertPopupShown) {
        UiAlertPopup(title = "수강신청 완료", content = "에듀윌과 합격하세요!\n무료특강 수강 신청이 완료 되었습니다.", onDismiss = {
            alertPopupShown = false
        }) {
            alertPopupShown = false
        }
    }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                modifier = Modifier.size(64.dp),
                alignment = Alignment.CenterStart,
                imageVector = Icons.Filled.Image, contentDescription = null
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "[2020] 공인중개사 중개사법령 및 중개실무 핵심이론 단과반(장석태)",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF222222)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "장석태 | 총 5강", color = Color(0xFF888888),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
        OutlinedButton(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            border = BorderStroke(Dp.Hairline, color = Color.Black),
            shape = RoundedCornerShape(4.dp),
            onClick = { alertPopupShown = true }) {
            Text(text = "수강 신청", color = Color(0xff333333))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    CourseCard()
}