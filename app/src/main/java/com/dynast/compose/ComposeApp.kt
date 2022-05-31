package com.dynast.compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeApp : Application() {
    companion object {
        val TAG: String = ComposeApp::class.java.simpleName
    }
}