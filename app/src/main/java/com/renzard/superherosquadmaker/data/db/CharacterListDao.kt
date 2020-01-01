package com.renzard.superherosquadmaker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renzard.superherosquadmaker.data.db.details.CharacterDetailEntryImpl
import com.renzard.superherosquadmaker.data.db.entity.Result
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntryImpl

@Dao
interface CharacterListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characterListEntries: List<Result>)


    @Query("select * from character_data")
    fun getCharacterList(): LiveData<List<CharacterListSimpleEntryImpl>>

    @Query("select * from character_data where id = :id")
    fun getCharacterDetails(id: Int): LiveData<CharacterDetailEntryImpl>
}