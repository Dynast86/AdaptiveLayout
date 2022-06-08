package com.dynast.compose.ui.components.free

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.compose.ui.theme.ComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownScreen(
    modifier: Modifier = Modifier
) {
    val options = listOf("과목 전체", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = modifier
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = modifier,
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    content = { Text(selectionOption) }
                )
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
fun DropDownScreenPreview() {
    ComposeTheme {
        DropDownScreen(modifier = Modifier.fillMaxWidth())
    }
}