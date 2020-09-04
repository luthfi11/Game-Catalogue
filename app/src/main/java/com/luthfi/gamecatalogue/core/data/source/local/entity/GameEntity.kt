package com.luthfi.gamecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    var isFavorite: Boolean? = false
)