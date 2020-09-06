package com.luthfi.gamecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (
    val id: Int?,
    val name: String?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val ratingCount: Int?,
    val isFavorite: Boolean
): Parcelable