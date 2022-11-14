package com.shaikhsoheb.marvelcharactersapp.data.remote.api

import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelApiResponse
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * An Retrofit contract to get Marvel APIs.
 */
interface MarvelCharactersApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("limit") limit: Int = 10): MarvelApiResponse<MarvelCharacterR?>
}
