package com.shaikhsoheb.marvelcharactersapp.data.remote.api

import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelApiResponse
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR

/**
 * An abstraction to hide away Retrofit's API contract.
 */

interface MarvelCharactersApiHelper {

    suspend fun getCharacters(): MarvelApiResponse<MarvelCharacterR?>
}