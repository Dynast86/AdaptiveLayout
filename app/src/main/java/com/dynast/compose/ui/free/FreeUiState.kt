package com.dynast.compose.ui.free

data class FreeUiState(
    val isLoading: Boolean = false,
    val data: List<CourseCardData> = emptyList(),
    val error: String? = null,
)