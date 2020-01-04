package com.renzard.superherosquadmaker.data.db.details

import com.google.gson.annotations.SerializedName


data class ComicEntryImpl(
    @SerializedName("comics_items_name")
    override val name: String,
    @SerializedName("comics_items_resourceURI")
    override val resourceURI: String
) : ComicEntry