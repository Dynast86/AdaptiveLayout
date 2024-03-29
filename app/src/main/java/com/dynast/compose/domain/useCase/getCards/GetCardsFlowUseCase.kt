package com.dynast.compose.domain.useCase.getCards

import androidx.paging.PagingData
import com.dynast.compose.data.remote.repository.MockupRepository
import com.dynast.compose.extension.di.IoDispatcher
import com.dynast.compose.ui.free.CourseCardData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetCardsFlowUseCase @Inject constructor(
    private val repository: MockupRepository,
    @IoDispatcher val coroutineDispatcher: CoroutineDispatcher
) {
    companion object {
        val TAG: String = GetCardsFlowUseCase::class.java.simpleName
    }

    suspend operator fun invoke(): Flow<PagingData<CourseCardData>> {
        return callbackFlow {
            repository.getCards(20).collectLatest {
                trySend(it)
            }
        }
    }
}