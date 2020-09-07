package com.luthfi.gamecatalogue.core.domain.usecase

import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGameList(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}