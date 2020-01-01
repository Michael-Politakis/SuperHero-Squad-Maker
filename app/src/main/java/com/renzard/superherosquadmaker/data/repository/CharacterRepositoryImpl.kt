package com.renzard.superherosquadmaker.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.CharacterListDao
import com.renzard.superherosquadmaker.data.db.details.CharacterDetailEntry
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntry
import com.renzard.superherosquadmaker.data.network.CHARACTER_LIMIT
import com.renzard.superherosquadmaker.data.network.CHARACTER_OFFSET
import com.renzard.superherosquadmaker.data.network.CharacterNetworkDataSource
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val characterListDao: CharacterListDao,
    private val characterNetworkDataSource: CharacterNetworkDataSource
) : CharacterRepository {

    //init with live data observer
    init {
        Log.d("debug", "data persistance")
        characterNetworkDataSource.apply {
            downloadedCharacterData.observeForever { fetchedCharacterData ->
                persistFetchedCharacterData(fetchedCharacterData)
            }
        }
    }

    //coroutine to return data
    override suspend fun getCharacterList(): LiveData<out List<CharacterListSimpleEntry>> {
        Log.d("debug", "Repo")
        return withContext(Dispatchers.IO) {
            initCharacterData()
            return@withContext characterListDao.getCharacterList()
        }
    }

    override suspend fun getCharacterDetails(id: Int): LiveData<out CharacterDetailEntry> {
        return withContext(Dispatchers.IO) {
            initCharacterData()
            return@withContext characterListDao.getCharacterDetails(id)
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
            fetchCharacterData()
    }

    //updates data
    private suspend fun fetchCharacterData() {
        characterNetworkDataSource.fetchCharacterData(CHARACTER_OFFSET, CHARACTER_LIMIT)
    }




}