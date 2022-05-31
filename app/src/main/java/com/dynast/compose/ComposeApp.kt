package com.dynast.compose

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeApp : Application(), ImageLoaderFactory {
    companion object {
        val TAG: String = ComposeApp::class.java.simpleName
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}