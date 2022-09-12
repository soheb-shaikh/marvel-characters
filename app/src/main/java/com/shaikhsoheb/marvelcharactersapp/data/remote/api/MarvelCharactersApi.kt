package com.shaikhsoheb.marvelcharactersapp.data.remote.api

import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelApiResponse
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * An Retrofit contract to get Marvel APIs.
 */
interface MarvelCharactersApi {
    @GET("/v1/public/characters")
    fun getCharacters(): MarvelApiResponse<MarvelCharacterR>
}
