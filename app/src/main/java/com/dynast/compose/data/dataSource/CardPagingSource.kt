package com.dynast.compose.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dynast.compose.data.ApiService
import com.dynast.compose.data.remote.dto.CourseModel
import com.dynast.compose.data.remote.repository.toCard
import com.dynast.compose.ui.free.CourseCardData
import javax.inject.Inject

class CardPagingSource @Inject constructor(
//    private val api: ApiService,
    private val index: Int,
) : PagingSource<Int, CourseCardData>() {
    companion object {
        val TAG: String = CardPagingSource::class.java.simpleName
    }

    private val courseCards: List<CourseModel> = getMockup()

    private fun getMockup(): List<CourseModel> {
        val items = mutableListOf<CourseModel>()
        repeat(100) {
            items.add(
                CourseModel(title = "[2020] 공인중개사 중개사법령 및 중개실무 핵심이론 단과반(장석태)", content = "장석태 | 총 ${it}강")
            )
        }
        return items.toList()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseCardData> {
        return try {
            val page = (params.key ?: (index - 1))

            LoadResult.Page(data = courseCards.toCard(), prevKey = 0, nextKey = page + 1)
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