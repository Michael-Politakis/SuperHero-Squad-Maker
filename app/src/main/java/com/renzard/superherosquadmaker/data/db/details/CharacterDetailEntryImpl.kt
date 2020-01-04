package com.renzard.superherosquadmaker.data.db.details

import androidx.room.ColumnInfo

data class CharacterDetailEntryImpl(
    @ColumnInfo(name = "id")
    override val characterId: Int,
    @ColumnInfo(name = "characterName")
    override val characterName: String,
    @ColumnInfo(name = "description")
    override val description: String,
    @ColumnInfo(name = "thumbnail_extension")
    override val thumbnailExtension: String,
    @ColumnInfo(name = "thumbnail_path")
    override val thumbnailPath: String


) : CharacterDetailEntry