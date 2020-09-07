package com.luthfi.gamecatalogue.core.utils

import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import com.luthfi.gamecatalogue.core.domain.model.Game

object DataMapper  {

    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingCount = it.ratingCount,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingCount = it.ratingCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        released = input.released,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingCount = input.ratingCount,
        isFavorite = input.isFavorite
    )
}