package com.luthfi.gamecatalogue.core.domain.repository

import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getGameList(): Flow<Resource<List<Game>>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}