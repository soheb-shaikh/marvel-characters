package com.shaikhsoheb.marvelcharactersapp.data.remote.api

import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelApiResponse
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR

interface MarvelCharactersApiHelper {

    suspend fun getCharacters(): MarvelApiResponse<MarvelCharacterR>
}