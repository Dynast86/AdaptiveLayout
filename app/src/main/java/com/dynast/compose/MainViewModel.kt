package com.dynast.compose

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.dynast.compose.domain.useCase.getCards.GetCardsFlowUseCase
import com.dynast.compose.domain.useCase.getCards.GetCardsUseCase
import com.dynast.compose.ui.free.CourseCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUIState(
    val courseCard : List<CourseCardData> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val getCardsFlowUseCase: GetCardsFlowUseCase,
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

    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState

    //    val getPagingData: Flow<PagingData<CourseCardData>> = Pager(
//        config = PagingConfig(
//            pageSize = 20,
//            enablePlaceholders = false
//        )
//    ) {
//        CardPagingSource()
//    }.flow.cachedIn(viewModelScope)
    private var _getPagingData: Flow<PagingData<CourseCardData>>? = null
    val getPagingData get() = _getPagingData!!
//    val getPagingData = Flow<PagingData<CourseCardData>>

    init {
        getCards()
    }

    private fun getCards() = viewModelScope.launch {
        _getPagingData = getCardsFlowUseCase()
    }

    fun setTopBarTitle(title: String) = viewModelScope.launch {
        _title.postValue(title)
    }

    fun setLogin() = viewModelScope.launch {
        _loginState.emit(true)
    }
}