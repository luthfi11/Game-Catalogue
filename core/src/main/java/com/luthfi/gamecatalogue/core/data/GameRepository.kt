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

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getPopularGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getPopularGames().map { DataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getPopularGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()

    override fun getUpcomingGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getUpcomingGames().map { DataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getUpcomingGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()

    override fun getTopRatedGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getTopRatedGames().map { DataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getTopRatedGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()

    override fun getGameDetail(id: String): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGameDetail(id.toInt()).map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: Game?): Boolean = data?.description == null

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getGameDetail(id)

            override suspend fun saveCallResult(data: GameResponse) {
                val game = DataMapper.mapResponseToEntity(data)
                localDataSource.insertGame(game)
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