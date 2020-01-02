package com.renzard.superherosquadmaker.data.db.selected

interface SelectedCharacters {
    val characterId: Int
    val thumbnailExtension: String
    val thumbnailPath: String
    val characterSelected: Boolean
}