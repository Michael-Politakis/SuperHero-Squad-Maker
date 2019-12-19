package com.renzard.superherosquadmaker.data.network.response

import com.renzard.superherosquadmaker.data.db.Result


data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)