package com.luthfi.gamecatalogue.core.domain.usecase

import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.domain.repository.IGameRepository

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getGameList() = gameRepository.getGameList()

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)
}