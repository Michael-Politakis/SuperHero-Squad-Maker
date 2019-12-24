package com.renzard.superherosquadmaker.data.db.entity


data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)