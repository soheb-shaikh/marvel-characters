package com.shaikhsoheb.marvelcharactersapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marvel_character")
data class MarvelCharacterL(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "bookmark") val isBookmarked: Boolean,
    @ColumnInfo(name = "thumbnail_url") val thumbnail_url: String
)
