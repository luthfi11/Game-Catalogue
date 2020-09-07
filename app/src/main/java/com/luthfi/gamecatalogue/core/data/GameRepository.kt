package com.luthfi.gamecatalogue.core.data

import com.luthfi.gamecatalogue.core.data.source.local.LocalDataSource
import com.luthfi.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.domain.repository.IGameRepository
import com.luthfi.gamecatalogue.core.utils.AppExecutors
import com.luthfi.gamecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getGameList(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getGameList().map { DataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getGameList()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }


}