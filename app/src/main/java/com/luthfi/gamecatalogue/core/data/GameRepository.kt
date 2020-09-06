package com.luthfi.gamecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.luthfi.gamecatalogue.core.data.source.local.LocalDataSource
import com.luthfi.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.domain.repository.IGameRepository
import com.luthfi.gamecatalogue.core.utils.AppExecutors
import com.luthfi.gamecatalogue.core.utils.DataMapper

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

    override fun getGameList(): LiveData<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Game>> {
                return Transformations.map(localDataSource.getGameList()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getGameList()

            override fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asLiveData()

    override fun getFavoriteGame(): LiveData<List<Game>> {
        return Transformations.map(localDataSource.getFavoriteGame()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }


}