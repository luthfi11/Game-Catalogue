package com.luthfi.gamecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luthfi.gamecatalogue.core.domain.model.Genre
import com.luthfi.gamecatalogue.core.domain.model.Screenshot

@Entity(tableName = "game")
data class GameEntity (
    @PrimaryKey
    @NonNull
    var id: Int?,
    var name: String?,
    var released: String?,
    var backgroundImage: String?,
    var rating: Double?,
    var ratingCount: Int?,
    var description: String?,
    var website: String?,
    var genres: List<Genre>?,
    var screenshot: List<Screenshot>?,
    var isFavorite: Boolean = false
)