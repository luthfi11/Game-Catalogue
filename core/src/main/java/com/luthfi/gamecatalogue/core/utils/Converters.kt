package com.luthfi.gamecatalogue.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luthfi.gamecatalogue.core.domain.model.Genre
import com.luthfi.gamecatalogue.core.domain.model.Screenshot

class Converters {
    @TypeConverter
    fun genreStringToList(source: String?): List<Genre>? {
        if (source == null) return null
        return Gson().fromJson(source, object : TypeToken<List<Genre>?>() {}.type)
    }

    @TypeConverter
    fun genreListToString(source: List<Genre>?): String? {
        if (source == null) return null
        return Gson().toJson(source)
    }

    @TypeConverter
    fun screenshotStringToList(source: String?): List<Screenshot>? {
        if (source == null) return null
        return Gson().fromJson(source, object : TypeToken<List<Screenshot>?>() {}.type)
    }

    @TypeConverter
    fun screenshotListToString(source: List<Screenshot>?): String? {
        if (source == null) return null
        return Gson().toJson(source)
    }
}