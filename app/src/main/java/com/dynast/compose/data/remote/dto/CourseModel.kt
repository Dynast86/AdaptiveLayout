package com.dynast.compose.data.remote.dto

import androidx.compose.runtime.Immutable

@Immutable
data class CourseModel(
    val title: String,
    val content: String,
    val imageUrl : String? = null
)
