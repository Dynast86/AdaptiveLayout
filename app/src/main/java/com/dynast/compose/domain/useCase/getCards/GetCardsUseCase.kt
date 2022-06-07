package com.dynast.compose.domain.useCase.getCards

import com.dynast.compose.data.remote.repository.MockupRepository
import com.dynast.compose.extension.Resource
import com.dynast.compose.extension.di.IoDispatcher
import com.dynast.compose.ui.free.CourseCardData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val repository: MockupRepository,
    @IoDispatcher val coroutineDispatcher: CoroutineDispatcher
) {
    companion object {
        val TAG: String = GetCardsUseCase::class.java.simpleName
    }

    suspend operator fun invoke(): Resource<List<CourseCardData>> {
        return try {
            withContext(context = coroutineDispatcher) {
                val resultDeferred = async { repository.getCards() }
                val resultData = resultDeferred.await()
                Resource.Success(data = resultData)
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "")
        }
    }
}