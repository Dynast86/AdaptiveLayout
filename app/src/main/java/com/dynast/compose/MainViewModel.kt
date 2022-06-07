package com.dynast.compose

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.dynast.compose.domain.useCase.getCards.GetCardsFlowUseCase
import com.dynast.compose.domain.useCase.getCards.GetCardsUseCase
import com.dynast.compose.extension.Resource
import com.dynast.compose.ui.free.CourseCardData
import com.dynast.compose.ui.free.FreeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    val getCardsFlowUseCase: GetCardsFlowUseCase,
    private val savedState: SavedStateHandle
) : ViewModel() {
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }

    private val _notify = MutableStateFlow(0)
    val notify: StateFlow<Int> get() = _notify

    private val _title = MutableLiveData("무료특강")
    val title: LiveData<String> get() = _title

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> get() = _loginState

    private var _freeUiState = MutableStateFlow(FreeUiState())
    val freeUiState get() = _freeUiState

    var temp: Flow<PagingData<CourseCardData>>? = null

    init {
        getCards()
        getPagingData()
    }

    private fun getPagingData() = viewModelScope.launch {
        temp = getCardsFlowUseCase()
    }

    private fun getCards() = viewModelScope.launch {
        _freeUiState.emit(
            when (val result = getCardsUseCase()) {
                is Resource.Success -> {
                    if (result.data != null) {
                        FreeUiState(data = result.data)
                    } else {
                        FreeUiState(data = emptyList())
                    }
                }
                is Resource.Error -> FreeUiState(error = result.message)
            }
        )
    }

    fun setTopBarTitle(title: String) = viewModelScope.launch {
        _title.postValue(title)
    }

    fun setLogin() = viewModelScope.launch {
        _loginState.emit(true)
    }
}