package com.luthfi.gamecatalogue.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity

interface GameDao {

    @Query("SELECT * FROM game")
    fun getGameList(): LiveData<List<GameEntity>>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGame(): LiveData<List<GameEntity>>

    @Insert
    fun insertGame(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(gameEntity: GameEntity)
}