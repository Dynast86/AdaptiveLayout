package com.dynast.compose.data.dataSource

import com.dynast.compose.data.ApiService
import com.dynast.compose.data.remote.dto.CourseModel
import javax.inject.Inject

class MockupDataSource @Inject constructor(
    private val api: ApiService
) {
    companion object {
        val TAG: String = MockupDataSource::class.java.simpleName
    }

    val courseCards: List<CourseModel> = getMockup()

    private fun getMockup(): List<CourseModel> {
        val items = mutableListOf<CourseModel>()
        repeat(100) {
            items.add(
                CourseModel(title = "[2020] 공인중개사 중개사법령 및 중개실무 핵심이론 단과반(장석태)", content = "장석태 | 총 ${it}강")
            )
        }
        return items.toList()
    }
}