package com.renzard.superherosquadmaker.data.network

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse


interface CharacterNetworkDataSource {
    val downloadedCharacterData: LiveData<CharacterResponse>

    suspend fun fetchCharacterData(characterOffset: Int, characterLimit: Int)
}