package com.dynast.compose.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dynast.compose.data.dataSource.CardPagingSource
import com.dynast.compose.data.dataSource.MockupDataSource
import com.dynast.compose.data.remote.dto.CourseModel
import com.dynast.compose.ui.free.CourseCardData
import javax.inject.Inject

class MockupRepositoryImpl @Inject constructor(
    val dataSource: MockupDataSource,
) : MockupRepository {
    companion object {
        val TAG: String = MockupRepositoryImpl::class.java.simpleName
    }

    override suspend fun getCards(): List<CourseCardData> = dataSource.courseCards.toCard()

    override suspend fun getCards(pageSize: Int) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        )
    ) {
        CardPagingSource(index = 0)
    }.flow
}

fun List<CourseModel>.toCard(): List<CourseCardData> = map {
    CourseCardData(title = it.title, content = it.content, imageUrl = it.imageUrl)
}