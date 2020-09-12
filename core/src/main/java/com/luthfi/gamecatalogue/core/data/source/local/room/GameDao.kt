package com.luthfi.gamecatalogue.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game WHERE ratingCount > 1000")
    fun getPopularGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE released IS NULL")
    fun getUpcomingGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE rating >= 4.8 AND released IS NOT NULL")
    fun getTopRatedGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getGameDetail(id: Int): Flow<GameEntity>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(game: List<GameEntity>)

    @RawQuery
    suspend fun updateGameData(query: SupportSQLiteQuery): GameEntity

    @Update
    fun updateFavoriteGame(gameEntity: GameEntity)
}