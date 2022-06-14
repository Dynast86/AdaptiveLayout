package com.dynast.compose.ui.components.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dynast.compose.MainViewModel
import com.dynast.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginSheet(
    state: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
//        layout_behavior
        sheetState = state,
        sheetShape = bottomShape,
        sheetContent = {
            LoginSheetItem(
                modifier = modifier,
                onLoginClicked = {
                    scope.launch {
                        viewModel.setLogin()
                        state.hide()
                    }
                },
                onJoinClicked = {}
            )
        }
    ) {}
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
fun LoginSheetItemPreview() {
    ComposeTheme {
        LoginSheetItem(onLoginClicked = {}, onJoinClicked = {})
    }
}