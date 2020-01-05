package com.renzard.superherosquadmaker.data.db.entity.characters

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<Result>
)