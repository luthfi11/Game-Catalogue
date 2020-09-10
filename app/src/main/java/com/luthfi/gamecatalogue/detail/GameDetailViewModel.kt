package com.luthfi.gamecatalogue.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase

class GameDetailViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    fun getGameDetail(id: String) = gameUseCase.getGameDetail(id).asLiveData()

    fun setFavoriteGame(game: Game, newState: Boolean) =
        gameUseCase.setFavoriteGame(game, newState)
}