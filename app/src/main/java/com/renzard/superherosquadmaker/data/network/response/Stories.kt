package com.renzard.superherosquadmaker.data.network.response


data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)