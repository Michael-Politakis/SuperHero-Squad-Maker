package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.CharacterDao
import com.renzard.superherosquadmaker.data.db.Result
import com.renzard.superherosquadmaker.data.network.CharacterNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CharacterRepositoryImpl(
    private val characterDao: CharacterDao,
    private val characterNetworkDataSource: CharacterNetworkDataSource
) : CharacterRepository {

    //init with live data observer
    init {
        characterNetworkDataSource.downloadedCharacterData.observeForever {
            //persist
        }
    }

    //coroutine to return data
    override suspend fun getCharacterList(): LiveData<Result> {
        return withContext(Dispatchers.IO) {
            return@withContext characterDao.getCharacterInfo()
        }
    }

    //data persisance
    private fun persistFetcedCharacterData(fetchedCharacters: Result) {
        GlobalScope.launch(Dispatchers.IO) {
            characterDao.upsert(fetchedCharacters)
        }
    }

    //checks if fetched data need update call update data if needed
    private suspend fun initCharacterData() {
        if (isFetchDataNeeded(ZonedDateTime.now())) {
            fetchCharacterData()
        }
    }

    //updates data
    private suspend fun fetchCharacterData() {
        characterNetworkDataSource.fetchCharacterData(
        )
    }


    //Helper function for data persistance
    private fun isFetchDataNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}