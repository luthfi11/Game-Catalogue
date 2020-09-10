package com.luthfi.gamecatalogue.core.data.source.local

import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity
import com.luthfi.gamecatalogue.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    fun getPopularGames() : Flow<List<GameEntity>> = gameDao.getPopularGames()

    fun getUpcomingGames() : Flow<List<GameEntity>> = gameDao.getUpcomingGames()

    fun getGameDetail(id: Int) : Flow<GameEntity> = gameDao.getGameDetail(id)

    fun getFavoriteGame() : Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGameList(game: List<GameEntity>) = gameDao.insertGameList(game)

    suspend fun insertGame(game: GameEntity) = gameDao.insertGame(game)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}