package com.dynast.compose.ui.myclass

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dynast.compose.MainViewModel

@Composable
fun MyClassScreen(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Text(text = "MyClassScreen")
}