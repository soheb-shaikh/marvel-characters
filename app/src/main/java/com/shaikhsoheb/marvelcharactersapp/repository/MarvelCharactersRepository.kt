package com.shaikhsoheb.marvelcharactersapp.repository

import com.shaikhsoheb.marvelcharactersapp.data.local.MarvelCharacterDao
import com.shaikhsoheb.marvelcharactersapp.data.local.model.MarvelCharacterL
import com.shaikhsoheb.marvelcharactersapp.data.remote.api.MarvelCharactersApiHelper
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelApiResponse
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelCharactersRepository @Inject constructor(
    private val apiHelper: MarvelCharactersApiHelper,
    private val dao: MarvelCharacterDao
) {
    suspend fun saveCharacters(charactersL: List<MarvelCharacterL>) {
        dao.insertListOfCharacters(charactersL)
    }

    suspend fun loadCharactersFromApi(): MarvelApiResponse<MarvelCharacterR?> {
        return apiHelper.getCharacters()
    }

    fun loadCharactersList(): Flow<List<MarvelCharacterL>> {
        return dao.getListOfCharacters()
    }

    suspend fun updateCharacter(character: MarvelCharacterL) {
        dao.updateCharacter(character)
    }

    fun getBookmarkedCharacters(): Flow<List<MarvelCharacterL>> {
        return dao.getBookMarkedCharacters()
    }
}