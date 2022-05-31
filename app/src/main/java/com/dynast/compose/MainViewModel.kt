package com.dynast.compose

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }
}