package com.renzard.superherosquadmaker.data.db.entity.comics


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comic_data", indices = [Index(value = ["id"], unique = true)])
data class Result(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Embedded(prefix = "comicThumbnail_")
    val thumbnail: Thumbnail,
    val title: String,
    @Embedded(prefix = "characters_")
    val characters: Characters
//    val upc: String,
//    val urls: List<Url>,
//    val variantDescription: String,
//    val variants: List<Any>
//    val images: List<Any>,
//    val isbn: String,
//    val issn: String,
//    val issueNumber: Int,
//    val modified: String,
//    val pageCount: Int,
//    val prices: List<Price>,
//    val resourceURI: String,
//    val series: Series,
//    val stories: Stories,
//    val textObjects: List<Any>,
//    val collectedIssues: List<Any>,
//    val collections: List<Any>,
//    val creators: Creators,
//    val dates: List<Date>,
//    val description: String,
//    val diamondCode: String,
//    val digitalId: Int,
//    val ean: String,
//    val events: Events,
//    val format: String,
)