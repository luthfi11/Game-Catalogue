package com.luthfi.gamecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.luthfi.gamecatalogue.core.data.source.local.entity.GameEntity
import com.luthfi.gamecatalogue.core.utils.Converters

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}