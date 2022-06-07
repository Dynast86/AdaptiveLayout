package com.dynast.compose.data.remote.repository

import androidx.paging.PagingData
import com.dynast.compose.ui.free.CourseCardData
import kotlinx.coroutines.flow.Flow

interface MockupRepository {

    companion object {
        val TAG: String = MockupRepository::class.java.simpleName
    }

    suspend fun getCards(): List<CourseCardData>
    suspend fun getCards(pageSize: Int): Flow<PagingData<CourseCardData>>
}