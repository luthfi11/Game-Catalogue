package com.luthfi.gamecatalogue.core.domain.model

data class Game (
    val id: Int?,
    val name: String?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val ratingCount: Int?,
    val description: String?,
    val website: String?,
    val genres: List<Genre>?,
    val screenshot: List<Screenshot>?,
    val isFavorite: Boolean
)