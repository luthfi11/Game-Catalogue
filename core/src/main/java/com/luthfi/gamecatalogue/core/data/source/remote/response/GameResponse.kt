package com.luthfi.gamecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse (
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("released")
    val released: String?,

    @field:SerializedName("background_image")
    val backgroundImage: String?,

    @field:SerializedName("rating")
    val rating: Double?,

    @field:SerializedName("ratings_count")
    val ratingCount: Int?,

    @field:SerializedName("description_raw")
    val description: String?,

    @field:SerializedName("website")
    val website: String?
)