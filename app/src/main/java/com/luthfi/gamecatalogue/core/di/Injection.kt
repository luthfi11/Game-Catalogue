package com.luthfi.gamecatalogue.core.di

import android.content.Context
import com.luthfi.gamecatalogue.core.data.GameRepository
import com.luthfi.gamecatalogue.core.data.source.local.LocalDataSource
import com.luthfi.gamecatalogue.core.data.source.local.room.GameDatabase
import com.luthfi.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiConfig
import com.luthfi.gamecatalogue.core.domain.repository.IGameRepository
import com.luthfi.gamecatalogue.core.domain.usecase.GameInteractor
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase
import com.luthfi.gamecatalogue.core.utils.AppExecutors

object Injection {

    private fun  provideRepository(context: Context): IGameRepository {
        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.gameDao())
        val appExecutors = AppExecutors()

        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideGameUseCase(context: Context) : GameUseCase {
        val repository = provideRepository(context)
        return GameInteractor(repository)
    }
}