package com.renzard.superherosquadmaker.data.db.entity.comics


data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)