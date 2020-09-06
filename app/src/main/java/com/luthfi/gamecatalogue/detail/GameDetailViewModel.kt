package com.luthfi.gamecatalogue.detail

import androidx.lifecycle.ViewModel
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase

class GameDetailViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun setFavoriteGame(game: Game, newState: Boolean) =
        gameUseCase.setFavoriteGame(game, newState)
}