package com.renzard.superherosquadmaker.data.network.response

import androidx.room.Embedded
import com.renzard.superherosquadmaker.data.db.entity.Data


data class CharacterResponse(
    @Embedded(prefix = "`data`_")
    val `data`: Data
//    val attributionHTML: String,
//    val attributionText: String,
//    val code: Int,
//    val copyright: String,
//    val etag: String,
//    val status: String
)