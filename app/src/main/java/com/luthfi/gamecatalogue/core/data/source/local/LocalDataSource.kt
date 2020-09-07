package com.luthfi.gamecatalogue.core.data.source.local

import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity
import com.luthfi.gamecatalogue.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    companion object {
        private var instance: LocalDataSource? = null
    }

    fun getGameList() : Flow<List<GameEntity>> = gameDao.getGameList()

    fun getFavoriteGame() : Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(game: List<GameEntity>) = gameDao.insertGame(game)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}