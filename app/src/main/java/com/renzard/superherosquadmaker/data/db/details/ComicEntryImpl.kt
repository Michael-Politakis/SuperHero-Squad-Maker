package com.renzard.superherosquadmaker.data.db.details

import androidx.room.ColumnInfo


data class ComicEntryImpl(
    @ColumnInfo(name = "id")
    override val comicId: Int,
    @ColumnInfo(name = "comicThumbnail_extension")
    override val comicExtension: String,
    @ColumnInfo(name = "comicThumbnail_path")
    override val comicPath: String,
    @ColumnInfo(name = "title")
    override val comicTitle: String,
    @ColumnInfo(name = "characters_returned")
    override val characterId: Int

) : ComicEntry