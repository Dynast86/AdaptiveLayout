package com.dynast.compose.ui.components.free

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.compose.ui.theme.ComposeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsScreen(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.background(Color.White),
    ) {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            repeat(5) { index ->
                FilterChip(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    selected = selectedItem == index,
                    selectedIcon = {
                        Image(imageVector = Icons.Outlined.Check, contentDescription = null)
                    },
                    onClick = { selectedItem = index },
                    label = { Text(text = "Chip $index") })
            }
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
fun ChipsScreenPreview() {
    ComposeTheme {
        ChipsScreen()
    }
}