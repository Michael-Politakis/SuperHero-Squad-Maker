package com.renzard.superherosquadmaker.data.db.selected

import androidx.room.ColumnInfo

data class SelectedCharactersImpl(
    @ColumnInfo(name = "id")
    override val characterId: Int,
    @ColumnInfo(name = "thumbnail_extension")
    override val thumbnailExtension: String,
    @ColumnInfo(name = "thumbnail_path")
    override val thumbnailPath: String,
    @ColumnInfo(name = "characterSelected")
    override val characterSelected: Boolean
) : SelectedCharacters