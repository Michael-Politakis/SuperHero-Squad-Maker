package com.renzard.superherosquadmaker.data.db.localized

import androidx.room.ColumnInfo

class CharacterListSimpleEntryImpl(

    @ColumnInfo(name = "name")
    override val characterName: String,
    @ColumnInfo(name = "thumbnail_extension")
    override val thumbnailExtension: String,
    @ColumnInfo(name = "thumbnail_path")
    override val thumbnailPath: String
) : CharacterListSimpleEntry
