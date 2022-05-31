package com.dynast.compose.myclass

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dynast.compose.MainViewModel

@Composable
fun MyClassScreen(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Text(text = "MyClassScreen")
}