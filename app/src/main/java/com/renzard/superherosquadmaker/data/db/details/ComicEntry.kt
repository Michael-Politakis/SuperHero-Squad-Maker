package com.renzard.superherosquadmaker.data.db.details


interface ComicEntry {
    val comicId: Int
    val comicExtension: String
    val comicPath: String
    val comicTitle: String
    val characterId: Int
}