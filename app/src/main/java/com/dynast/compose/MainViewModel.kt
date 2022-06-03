package com.dynast.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }

    private val _notify = MutableStateFlow(0)
    val notify: StateFlow<Int> get() = _notify

    private val _title = MutableLiveData("무료특강")
    val title: LiveData<String> get() = _title


    fun setTopBarTitle(title: String) = viewModelScope.launch {
        _title.postValue(title)
    }
}