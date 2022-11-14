package com.shaikhsoheb.marvelcharactersapp.data

/**
 * A class to hold resource fetch state.
 */
data class MarvelCharacterResource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): MarvelCharacterResource<T> {
            return MarvelCharacterResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T?): MarvelCharacterResource<T> {
            return MarvelCharacterResource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T?): MarvelCharacterResource<T> {
            return MarvelCharacterResource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
