package com.dynast.compose.domain

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val settingsDataStore = context.dataStore
}