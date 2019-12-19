package com.renzard.superherosquadmaker.data.network

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.Result

interface CharacterNetworkDataSource {
    val downloadedCharacterData: LiveData<Result>

    suspend fun fetchCharacterData(
//        name: String,
//        comics: Comics,
//        description: String,
//        events: Events,
//        id: Int,
//        modified: String,
//        resourceURI: String,
//        eries: Series,
//        stories: Stories,
//        thumbnail: Thumbnail,
//        urls: List<Url>
    )
}