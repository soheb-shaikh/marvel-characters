package com.shaikhsoheb.marvelcharactersapp.data.local

import androidx.room.*
import com.shaikhsoheb.marvelcharactersapp.data.local.model.MarvelCharacterL
import kotlinx.coroutines.flow.Flow

/**
 * A Data access object to abstract away database.
 */
@Dao
interface MarvelCharacterDao {

    @Query("SELECT * FROM marvel_character")
    fun getListOfCharacters(): Flow<List<MarvelCharacterL>>

    @Query("SELECT * FROM marvel_character WHERE bookmark = 1")
    fun getBookMarkedCharacters(): Flow<List<MarvelCharacterL>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCharacter(character: MarvelCharacterL)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListOfCharacters(characters: List<MarvelCharacterL>)
}