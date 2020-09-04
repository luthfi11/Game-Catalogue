package com.luthfi.gamecatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game

interface IGameRepository {

    fun getGameList(): LiveData<Resource<List<Game>>>

    fun getFavoriteGame(): LiveData<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}