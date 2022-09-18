package com.shaikhsoheb.marvelcharactersapp.repository

import com.shaikhsoheb.marvelcharactersapp.data.MarvelCharacterResource
import com.shaikhsoheb.marvelcharactersapp.data.local.MarvelCharacterDao
import com.shaikhsoheb.marvelcharactersapp.data.local.model.MarvelCharacterL
import com.shaikhsoheb.marvelcharactersapp.data.remote.api.MarvelCharactersApiHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A repository which acts as a single source of truth for app data.
 */
class MarvelCharactersRepository @Inject constructor(
    private val apiHelper: MarvelCharactersApiHelper,
    private val dao: MarvelCharacterDao
) {
    suspend fun saveCharacters(charactersL: List<MarvelCharacterL>) {
        dao.insertListOfCharacters(charactersL)
    }

    /**
     * This method will fetch marvel characters from api and load it into database.
     * @return the characters from database
     */
    suspend fun loadCharacters(): MarvelCharacterResource<List<MarvelCharacterL>> {
            val response = apiHelper.getCharacters()
            if (response.code != 200) {
                return MarvelCharacterResource.error(response.status, emptyList())
            }
            val apiList = response.data.results

            // Insert into database
            dao.insertListOfCharacters(
                apiList.map { it.mapToMarvelCharacterL() }
            )

        return MarvelCharacterResource.success(dao.getListOfCharacters())
    }

    suspend fun updateCharacter(character: MarvelCharacterL) {
        dao.updateCharacter(character)
    }

    fun getBookmarkedCharacters(): Flow<List<MarvelCharacterL>> {
        return dao.getBookMarkedCharacters()
    }
}