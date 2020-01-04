package com.renzard.superherosquadmaker.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "character_data", indices = [Index(value = ["id"], unique = true)])
data class Result(
    @SerializedName("name")
    val characterName: String,
    @SerializedName("description")
    val description: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @Embedded(prefix = "thumbnail_")
    val thumbnail: Thumbnail,
    @SerializedName("characterSelected")
    var characterSelected: Boolean = false
//    @Embedded(prefix = "comics_")
//    val comics: Item
//    val urls: List<Url>
//    val modified: String,
//    val resourceURI: String,
//    val series: Series,
//    val stories: Stories,
//    val events: Events,
)