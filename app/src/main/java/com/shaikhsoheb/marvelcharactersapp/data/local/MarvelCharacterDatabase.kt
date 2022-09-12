package com.shaikhsoheb.marvelcharactersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * An offline in-app storage for Marvel Characters.
 */
@Database(
    entities = [MarvelCharacterL::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelCharacterDatabase: RoomDatabase() {

    abstract fun getMarvelCharacterDao(): MarvelCharacterDao
}