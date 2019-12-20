package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.Result

interface CharacterRepository {
    suspend fun getCharacterList(): LiveData<Result>
}