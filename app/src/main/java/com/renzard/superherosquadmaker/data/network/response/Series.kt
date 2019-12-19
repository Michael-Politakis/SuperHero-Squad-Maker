package com.renzard.superherosquadmaker.data.network.response


data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)