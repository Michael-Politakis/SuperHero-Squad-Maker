package com.renzard.superherosquadmaker.data.db.entity.comics


data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)