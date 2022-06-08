package com.dynast.compose.ui.components.mypage

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dynast.compose.ui.theme.ComposeTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyPageTabScreen(
    modifier: Modifier = Modifier,
    visible: Boolean = false
) {
    val currentState = remember { MutableTransitionState(visible) }

    Column {
        ListItem(
            modifier = Modifier.clickable {
                currentState.targetState = !currentState.targetState
            },
            text = { Text(text = "마이페이지") },
            trailing = {
                Image(
                    imageVector = if (!currentState.targetState) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
                    contentDescription = null
                )
            }
        )
        repeat(3) {
            ExpandableContent(title = "Item $it", currentState = currentState)
        }
        Divider()
        var switched by remember { mutableStateOf(false) }
        val onSwitchedChange: (Boolean) -> Unit = { switched = it }
        ListItem(
            text = { Text("Switch ListItem") },
            singleLineSecondaryText = true,
            secondaryText = {
                Text(
                    "This is a long secondary text for the current list item, " +
                            "displayed on two lines blah blah blah blah blah"
                )
            },
            trailing = {
                Switch(
                    checked = switched,
                    onCheckedChange = null // null recommended for accessibility with screenreaders
                )
            },
            modifier = Modifier.toggleable(
                value = switched,
                onValueChange = onSwitchedChange
            )
        )
        repeat(3) {
            var checked by remember { mutableStateOf(true) }
            val onCheckedChange: (Boolean) -> Unit = { checked = it }
            ListItem(
                text = { Text("Checkbox ListItem Title") },
                overlineText = { Text("OVERLINE") },
                secondaryText = {
                    Text(
                        "This is a long secondary text for the current list item " +
                                "displayed on two lines"
                    )
                },
                singleLineSecondaryText = false,
                trailing = {
                    Checkbox(checked = checked, onCheckedChange = null)
                },
                modifier = Modifier.toggleable(
                    value = checked,
                    onValueChange = onCheckedChange
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableContent(
    title: String,
    currentState: MutableTransitionState<Boolean>
) {
    AnimatedVisibility(visibleState = currentState) {
        ListItem(
            text = { Text(title) },
            icon = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Localized description"
                )
            },
            modifier = Modifier.clickable { }
        )
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
fun MyPageTabScreenPreview() {
    ComposeTheme {
        MyPageTabScreen(visible = true)
    }
}