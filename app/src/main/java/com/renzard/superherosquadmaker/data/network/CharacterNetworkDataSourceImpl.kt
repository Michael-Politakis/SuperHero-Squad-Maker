package com.renzard.superherosquadmaker.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renzard.superherosquadmaker.data.network.apiRequest.MarvelApiService
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse
import com.renzard.superherosquadmaker.internal.NoConnectivityException


const val CHARACTER_LIMIT = 20
const val CHARACTER_OFFSET = 0
class CharacterNetworkDataSourceImpl(
    private val marvelApiService: MarvelApiService
) : CharacterNetworkDataSource {

    private val _downloadedCharacterData = MutableLiveData<CharacterResponse>()
    override val downloadedCharacterData: LiveData<CharacterResponse>
        get() = _downloadedCharacterData

    override suspend fun fetchCharacterData(
        characterOffset: Int, characterLimit: Int
    ) {
        try {
            val fetchedCharacterData = marvelApiService
                .getAllCharacters(CHARACTER_OFFSET, CHARACTER_LIMIT)
                .await()
            _downloadedCharacterData.postValue(fetchedCharacterData)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection")
        }
    }
}