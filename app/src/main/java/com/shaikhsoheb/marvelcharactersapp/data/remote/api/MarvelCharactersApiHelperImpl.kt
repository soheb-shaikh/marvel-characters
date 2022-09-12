package com.shaikhsoheb.marvelcharactersapp.data.remote.api

import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelApiResponse
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR
import javax.inject.Inject

class MarvelCharactersApiHelperImpl @Inject constructor(
    private val api: MarvelCharactersApi
): MarvelCharactersApiHelper {
    override suspend fun getCharacters(): MarvelApiResponse<MarvelCharacterR> {
        return api.getCharacters()
    }
}
