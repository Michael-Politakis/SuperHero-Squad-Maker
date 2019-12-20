package com.renzard.superherosquadmaker.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renzard.superherosquadmaker.data.db.Result
import com.renzard.superherosquadmaker.data.network.apiRequest.MarvelApiService
import com.renzard.superherosquadmaker.internal.NoConnectivityException

class CharacterNetworkDataSourceImpl(
    private val marvelApiService: MarvelApiService
) : CharacterNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<Result>()
    override val downloadedCharacterData: LiveData<Result>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCharacterData(
    ) {
        try {
            val fetchedCharacterData = marvelApiService.getAllCharacters()
                .await()
            _downloadedCurrentWeather.postValue(fetchedCharacterData)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection")
        }
    }
}