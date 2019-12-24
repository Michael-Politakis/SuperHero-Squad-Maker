package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.CharacterListDao
import com.renzard.superherosquadmaker.data.db.localized.CharacterListSimpleEntry
import com.renzard.superherosquadmaker.data.network.CHARACTER_LIMIT
import com.renzard.superherosquadmaker.data.network.CHARACTER_OFFSET
import com.renzard.superherosquadmaker.data.network.CharacterNetworkDataSource
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CharacterRepositoryImpl(
    private val characterListDao: CharacterListDao,
    private val characterNetworkDataSource: CharacterNetworkDataSource
) : CharacterRepository {

    //init with live data observer
    init {
        characterNetworkDataSource.apply {
            downloadedCharacterData.observeForever { fetchedCharacterData ->
                persistFetchedCharacterData(fetchedCharacterData)
            }
        }
    }

    //coroutine to return data
    override suspend fun getCharacterList(): LiveData<out List<CharacterListSimpleEntry>> {
        return withContext(Dispatchers.IO) {
            initCharacterData()
            return@withContext characterListDao.getCharacterList()
        }
    }

    //data persisance
    private fun persistFetchedCharacterData(fetchedCharacterData: CharacterResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val fetchedCharacterList = fetchedCharacterData.data.results
            characterListDao.insert(fetchedCharacterList)
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
        characterNetworkDataSource.fetchCharacterData(CHARACTER_OFFSET, CHARACTER_LIMIT)
    }


    //Helper function for data persistance
    private fun isFetchDataNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }


}