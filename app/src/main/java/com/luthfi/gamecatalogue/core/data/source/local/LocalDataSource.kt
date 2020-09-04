package com.luthfi.gamecatalogue.core.data.source.local

import androidx.lifecycle.LiveData
import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity
import com.luthfi.gamecatalogue.core.data.source.local.room.GameDao

class LocalDataSource private constructor(private val gameDao: GameDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(gameDao: GameDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(gameDao)
            }
    }

    fun getGameList() : LiveData<List<GameEntity>> = gameDao.getGameList()

    fun getFavoriteGame() : LiveData<List<GameEntity>> = gameDao.getFavoriteGame()

    fun insertGame(game: List<GameEntity>) = gameDao.insertGame(game)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}