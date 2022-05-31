package com.dynast.compose.ui.components.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginSheet(
    state: ModalBottomSheetState,
    modifier: Modifier = Modifier
) {
    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = bottomShape,
        sheetContent = {
            LoginSheetItem(
                modifier = modifier,
                onLoginClicked = {},
                onJoinClicked = {}
            )
        },
        content = {}
    )
}

@Composable
fun LoginSheetItem(
    modifier: Modifier = Modifier,
    onLoginClicked: () -> Unit,
    onJoinClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(text = "에듀윌 회원에게만 제공", fontWeight = FontWeight.Bold)
            Text(text = "되는")
        }
        Text(text = "다양한 서비스를 누려보세요.")
        Spacer(Modifier.height(16.dp))
        OutlinedButton(modifier = modifier.fillMaxWidth(), onClick = onLoginClicked) {
            Text(text = "로그인")
        }
        OutlinedButton(modifier = modifier.fillMaxWidth(), onClick = onJoinClicked) {
            Text(text = "에듀윌 가입하고 합격하기")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginSheetItemPreview() {
    LoginSheetItem(onLoginClicked = {}, onJoinClicked = {})
}