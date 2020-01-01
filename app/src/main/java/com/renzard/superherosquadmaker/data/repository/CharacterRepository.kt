package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.details.CharacterDetailEntry
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntry

interface CharacterRepository {
    suspend fun getCharacterList(): LiveData<out List<CharacterListSimpleEntry>>

    suspend fun getCharacterDetails(id: Int): LiveData<out CharacterDetailEntry>
}