package com.luthfi.gamecatalogue.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getGameList(): LiveData<List<GameEntity>>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGame(): LiveData<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(gameEntity: GameEntity)
}