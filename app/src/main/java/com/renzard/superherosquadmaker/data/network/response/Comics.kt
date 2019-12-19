package com.renzard.superherosquadmaker.data.network.response


data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)