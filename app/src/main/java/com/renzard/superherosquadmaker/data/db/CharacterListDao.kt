package com.renzard.superherosquadmaker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renzard.superherosquadmaker.data.db.entity.Result
import com.renzard.superherosquadmaker.data.db.localized.CharacterListSimpleEntryImpl

@Dao
interface CharacterListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characterListEntries: List<Result>)


    @Query("select * from character_data")
    fun getCharacterList(): LiveData<List<CharacterListSimpleEntryImpl>>
}