package com.shaikhsoheb.marvelcharactersapp.repository

import com.shaikhsoheb.marvelcharactersapp.data.local.model.MarvelCharacterL
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR

fun MarvelCharacterR?.mapToMarvelCharacterL(): MarvelCharacterL {
    return MarvelCharacterL(
        id = this?.id ?: Int.MIN_VALUE,
        name = this?.name.orEmpty(),
        description = this?.description.orEmpty(),
        thumbnail_url = this?.thumbnail?.getUrl().orEmpty(),
        isBookmarked = false
    )
}