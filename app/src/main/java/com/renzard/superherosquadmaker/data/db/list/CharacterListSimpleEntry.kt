package com.renzard.superherosquadmaker.data.db.list

interface CharacterListSimpleEntry {
    val characterId: Int
    val characterName: String
    val thumbnailExtension: String
    val thumbnailPath: String
}