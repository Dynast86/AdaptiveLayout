package com.dynast.compose.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dynast.compose.MainViewModel

@Composable
fun FavoriteScreen(
//    widthSize = widthSize,
    mainViewModel: MainViewModel,
    modifier: Modifier
) {
    Text(text = "Favorite")
}