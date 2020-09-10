package com.luthfi.gamecatalogue.core.domain.usecase

import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getPopularGames() = gameRepository.getPopularGames()

    override fun getUpcomingGames(): Flow<Resource<List<Game>>> = gameRepository.getUpcomingGames()

    override fun getGameDetail(id: String): Flow<Resource<Game>> = gameRepository.getGameDetail(id)

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)
}