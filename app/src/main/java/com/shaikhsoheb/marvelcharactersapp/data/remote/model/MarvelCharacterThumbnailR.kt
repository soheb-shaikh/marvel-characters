package com.shaikhsoheb.marvelcharactersapp.data.remote.model

import com.squareup.moshi.Json

data class MarvelCharacterThumbnailR(
    @Json(name = "path")
    var path: String,

    @Json(name = "extension")
    var extension: String
) {
    fun getUrl() = "$path.$extension".replace("http", "https")
}