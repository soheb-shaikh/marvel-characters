package com.shaikhsoheb.marvelcharactersapp.data.remote.model

import com.squareup.moshi.Json

data class MarvelCharacterR(
    @Json(name = "id")
    var id: Int,

    @Json(name = "name")
    var name: String,

    @Json(name = "description")
    var description: String,

    @Json(name = "thumbnail")
    var thumbnail: MarvelCharacterThumbnailR,
)
