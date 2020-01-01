package com.renzard.superherosquadmaker.data.db.details

interface CharacterDetailEntry {
    val characterId: Int
    val characterName: String
    //    val comicsItems: List<Any>
    val description: String
    val thumbnailExtension: String
    val thumbnailPath: String
}