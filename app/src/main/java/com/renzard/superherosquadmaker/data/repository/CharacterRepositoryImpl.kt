package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.CharacterListDao
import com.renzard.superherosquadmaker.data.db.details.CharacterDetailEntry
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntry
import com.renzard.superherosquadmaker.data.db.selected.SelectedCharacters
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

    override suspend fun getCharacterDetails(id: Int): LiveData<out CharacterDetailEntry> {
        return withContext(Dispatchers.IO) {
            initCharacterData()
            return@withContext characterListDao.getCharacterDetails(id)
        }
    }

    override suspend fun getSelected(): LiveData<out List<SelectedCharacters>> {
        return withContext(Dispatchers.IO) {
            initCharacterData()
            return@withContext characterListDao.getSelected()
        }
    }

    override suspend fun getSelectedID(id: Int): LiveData<out SelectedCharacters> {
        return withContext(Dispatchers.IO) {
            initCharacterData()
            return@withContext characterListDao.getSelectedId(id)
        }
    }

    override suspend fun setSelected(selectedHeroId: Int) {
        characterListDao.setSelected(selectedHeroId)
    }

    override suspend fun setNotSelected(selectedHeroId: Int) {
        characterListDao.setNotSelected(selectedHeroId)
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