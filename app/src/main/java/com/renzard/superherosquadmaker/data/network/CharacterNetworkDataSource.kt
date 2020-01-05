package com.renzard.superherosquadmaker.data.network

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.entity.comics.ComicResponse
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse


interface CharacterNetworkDataSource {
    val downloadedCharacterData: LiveData<CharacterResponse>
    val downloadedComicData: LiveData<ComicResponse>

    suspend fun fetchCharacterData(characterOffset: Int, characterLimit: Int)
    suspend fun fetchComicData(characterId: Int)
}