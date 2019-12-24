package com.renzard.superherosquadmaker.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "character_data", indices = [Index(value = ["id"], unique = true)])
data class Result(
    @PrimaryKey(autoGenerate = true)
    val databaseId: Int? = null,
    @SerializedName("name")
    val characterName: String,
    @Embedded(prefix = "comics_")
    val comics: Comics,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @Embedded(prefix = "thumbnail_")
    val thumbnail: Thumbnail
//    val urls: List<Url>
//    val modified: String,
//    val resourceURI: String,
//    val series: Series,
//    val stories: Stories,
//    val events: Events,
)