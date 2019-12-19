package com.renzard.superherosquadmaker.data.db


import androidx.room.Embedded
import androidx.room.Entity
import com.renzard.superherosquadmaker.data.network.response.*


@Entity(tableName = "character")
data class Result(
    @Embedded(prefix = "comics_")
    val comics: Comics,
    val description: String,
    @Embedded(prefix = "events_")
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    @Embedded(prefix = "series_")
    val series: Series,
    @Embedded(prefix = "stories_")
    val stories: Stories,
    @Embedded(prefix = "thumbnail_")
    val thumbnail: Thumbnail,
    @Embedded(prefix = "urls_")
    val urls: List<Url>
)