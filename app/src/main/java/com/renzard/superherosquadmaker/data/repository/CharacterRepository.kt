package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.localized.CharacterListSimpleEntry

interface CharacterRepository {
    suspend fun getCharacterList(): LiveData<out List<CharacterListSimpleEntry>>
}