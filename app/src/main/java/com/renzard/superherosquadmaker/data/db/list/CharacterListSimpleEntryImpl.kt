package com.renzard.superherosquadmaker.data.db.list

import androidx.room.ColumnInfo

class CharacterListSimpleEntryImpl(
    @ColumnInfo(name = "id")
    override val characterId: Int,
    @ColumnInfo(name = "characterName")
    override val characterName: String,
    @ColumnInfo(name = "thumbnail_extension")
    override val thumbnailExtension: String,
    @ColumnInfo(name = "thumbnail_path")
    override val thumbnailPath: String
) : CharacterListSimpleEntry