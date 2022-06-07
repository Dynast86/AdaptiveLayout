package com.dynast.compose.extension.di

import com.dynast.compose.data.dataSource.CardPagingSource
import com.dynast.compose.data.dataSource.MockupDataSource
import com.dynast.compose.data.remote.repository.MockupRepository
import com.dynast.compose.data.remote.repository.MockupRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMockupRepository(dataSource: MockupDataSource): MockupRepository =
        MockupRepositoryImpl(dataSource = dataSource)

}