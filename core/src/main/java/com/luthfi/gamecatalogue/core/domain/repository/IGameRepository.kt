package com.luthfi.gamecatalogue.core.domain.repository

import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getPopularGames(): Flow<Resource<List<Game>>>

    fun getUpcomingGames(): Flow<Resource<List<Game>>>

    fun getTopRatedGames(): Flow<Resource<List<Game>>>

    fun getGameDetail(id: String): Flow<Resource<Game>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}