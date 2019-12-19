package com.renzard.superherosquadmaker.network.response


data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)