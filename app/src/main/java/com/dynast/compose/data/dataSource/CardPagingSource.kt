package com.dynast.compose.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dynast.compose.data.remote.dto.CourseModel
import com.dynast.compose.data.remote.repository.toCard
import com.dynast.compose.ui.free.CourseCardData

class CardPagingSource : PagingSource<Int, CourseCardData>() {
    companion object {
        val TAG: String = CardPagingSource::class.java.simpleName
    }

    private val courseCards: List<CourseModel> = getMockup()

    private fun getMockup(): List<CourseModel> {
        val items = mutableListOf<CourseModel>()
        repeat(50) {
            items.add(
                CourseModel(title = "PagingSource $it", content = "TEST | 총 ${it}강")
            )
        }
        return items.toList()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseCardData> {
        return try {
            val page = params.key

            LoadResult.Page(data = courseCards.toCard(), prevKey = page, nextKey = page?.plus(1))
        } catch (e: Exception) {
            LoadResult.Error(throwable = Throwable("Paging Error"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CourseCardData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}